package hu.autsoft.rainbowcake.base

import hu.autsoft.rainbowcake.Contexts
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import kotlin.coroutines.experimental.CoroutineContext


/**
 * A ViewModel base class that in addition to providing state handling via [BaseViewModel]
 * also provides the ability to easily start coroutines in the UI context.
 */
abstract class JobViewModel<VS : Any>(initialState: VS) : BaseViewModel<VS>(initialState), CoroutineScope {

    /**
     * The empty parent [Job] of all coroutines launched from this ViewModel.
     * This Job is cancelled when the ViewModel is cleared, which also
     * cancels all its children coroutines.
     */
    private val rootJob = Job()

    /**
     * Implementation of the [CoroutineScope] interface. Coroutines launched
     * in this scope will get their dispatcher from the UI context (i.e. run
     * on the main thread) and have [rootJob] as their parent.
     */
    final override val coroutineContext: CoroutineContext
        get() = Contexts.UI + rootJob

    override fun onCleared() {
        super.onCleared()
        rootJob.cancel()
        Timber.d("ViewModel cleared, rootJob cancelled")
    }

    /**
     * The flag indicating that a blocking [execute] is currently in progress.
     * A primitive Boolean without synchronization is safe to use here since
     * it will only ever be accessed on the UI thread.
     */
    private var busy = false

    /**
     * Executes the given [task] in a coroutine in the ViewModel's scope
     * (on the UI thread, with cancellation if the ViewModel is cleared).
     *
     * These calls are "blocking" by default, in the sense that if a previous
     * [execute] call is still ongoing (even if suspended or currently on
     * another thread), any other [execute] calls will silently terminate
     * as no-ops. This behaviour, for example, prevents launching duplicate
     * actions on quick repeated button presses, which is a common issue.
     *
     * Any exceptions not caught by the [task] will be caught and consumed
     * by this method.
     *
     * @param blocking Whether this [execute] call should block other job
     *                 launches via [execute] until it completes.
     */
    @Suppress("TooGenericExceptionCaught")
    protected fun execute(blocking: Boolean = true, task: suspend () -> Unit) {
        launch {
            if (blocking) {
                if (busy) {
                    Timber.d("Denying job launch, busy")
                    return@launch
                }
                busy = true
            }

            try {
                task()
            } catch (e: CancellationException) {
                Timber.d("Job cancelled exception:")
                Timber.d(e)
            } catch (e: Exception) {
                Timber.d("Unhandled exception in ViewModel:")
                Timber.d(e)
            } finally {
                if (blocking) {
                    busy = false
                }
            }
        }
    }

    /**
     * Convenience method for running [execute] in a non-blocking way with a
     * cleaner syntax.
     */
    protected fun executeNonBlocking(task: suspend () -> Unit) = execute(blocking = false, task = task)

}
