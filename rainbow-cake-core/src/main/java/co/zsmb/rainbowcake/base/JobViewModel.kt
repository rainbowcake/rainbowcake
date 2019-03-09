package co.zsmb.rainbowcake.base

import android.support.annotation.CallSuper
import co.zsmb.rainbowcake.Contexts
import co.zsmb.rainbowcake.internal.config.RainbowCakeConfiguration
import co.zsmb.rainbowcake.internal.logging.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


/**
 * A ViewModel base class that in addition to providing state handling via [RainbowCakeViewModel]
 * also provides the ability to easily start coroutines in the UI context.
 */
abstract class JobViewModel<VS : Any>(initialState: VS) : RainbowCakeViewModel<VS>(initialState), CoroutineScope {

    /**
     * The empty parent [Job] of all coroutines launched from this ViewModel.
     * This Job is cancelled when the ViewModel is cleared, which also
     * cancels all its children coroutines.
     */
    private val rootJob: Job = SupervisorJob()

    /**
     * Implementation of the [CoroutineScope] interface. Coroutines launched
     * in this scope will get their dispatcher from the UI context (i.e. run
     * on the main thread) and have [rootJob] as their parent.
     */
    final override val coroutineContext = Contexts.UI + rootJob

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        rootJob.cancel()
        log("ViewModel cleared, rootJob cancelled")
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
    @Suppress("RedundantUnitReturnType")
    protected fun execute(blocking: Boolean = true, task: suspend () -> Unit): Unit {
        executeImpl(blocking, task)
    }

    /**
     * Convenience method for running [execute] in a non-blocking way with a
     * (subjectively) cleaner syntax.
     */
    @Suppress("RedundantUnitReturnType")
    protected fun executeNonBlocking(task: suspend () -> Unit): Unit {
        executeImpl(blocking = false, task = task)
    }

    /**
     * A variation of [execute] that returns the Job for the coroutine it
     * started, mainly for manual coroutine cancellation purposes.
     */
    protected fun executeCancellable(blocking: Boolean = true, task: suspend () -> Unit): Job {
        return executeImpl(blocking, task)
    }

    /**
     * Actual private implementation of executing a given task. For details,
     * see [execute], which is the usual entry point to this method.
     */
    private fun executeImpl(blocking: Boolean = true, task: suspend () -> Unit): Job {
        return launch {
            if (blocking) {
                if (busy) {
                    log("Denying job launch, busy")
                    return@launch
                }
                busy = true
            }

            try {
                if (RainbowCakeConfiguration.consumeExecuteExceptions) {
                    consumeExceptions {
                        task()
                    }
                } else {
                    task()
                }
            } finally {
                if (blocking) {
                    busy = false
                }
            }
        }
    }

    private suspend fun consumeExceptions(task: suspend () -> Unit) {
        try {
            task()
        } catch (e: CancellationException) {
            log("Job cancelled exception:")
            log(e)
        } catch (e: Exception) {
            log("Unhandled exception in ViewModel:")
            log(e)
        }
    }

}
