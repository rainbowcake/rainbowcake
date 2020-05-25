@file:Suppress("RedundantVisibilityModifier")

package co.zsmb.rainbowcake.base

import androidx.annotation.CallSuper
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.internal.config.RainbowCakeConfiguration
import co.zsmb.rainbowcake.internal.livedata.ActiveOnlySingleShotLiveData
import co.zsmb.rainbowcake.internal.livedata.ClairvoyantLiveData
import co.zsmb.rainbowcake.internal.livedata.LiveDataCollection
import co.zsmb.rainbowcake.internal.livedata.MutableLiveDataCollection
import co.zsmb.rainbowcake.internal.livedata.MutableLiveDataCollectionImpl
import co.zsmb.rainbowcake.internal.livedata.QueuedSingleShotLiveData
import co.zsmb.rainbowcake.internal.livedata.SingleShotLiveData
import co.zsmb.rainbowcake.internal.livedata.distinct
import co.zsmb.rainbowcake.internal.logging.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * A ViewModel base class that provides:
 * - safe view state handling via [LiveData],
 * - one-time event support via [SingleShotLiveData],
 * - the ability to easily start coroutines in a UI context.
 */
abstract class RainbowCakeViewModel<VS : Any>(initialState: VS) : ViewModel() {

    //region State
    /**
     * The [MutableLiveData] instance actually containing the current view state.
     */
    private val _state: ClairvoyantLiveData<VS> = ClairvoyantLiveData()

    init {
        // This initialization ensures that _state never holds a null value
        _state.value = initialState
    }

    /**
     * The [LiveData] to be observed by the [RainbowCakeFragment] or
     * [RainbowCakeActivity] connected to this ViewModel to receive state
     * updates. This is a read-only view of the contained [MutableLiveData]
     * and is filtered so that it only emits distinct values (i.e. subsequent
     * duplicates don't trigger updates on it).
     */
    public val state: LiveData<VS> = _state.distinct()

    /**
     * The view state exposed to the ViewModel subclasses to be able to read the
     * current state and set new state. Exposes the underlying [MutableLiveData]
     * as if it was a single property of type [VS] for convenience.
     *
     * This property can only be accessed from the main (UI) thread.
     */
    protected var viewState: VS
        get() = _state.value!!
        set(value) {
            _state.placeValue(value)
        }

    /**
     * Sets the view state. This method may be called from a background thread.
     *
     * You should not need to use this, as [execute] provides an easy way to
     * launch background tasks and get back to the UI via coroutines. To issue
     * updates originating from lower layers, use coroutine Flows.
     */
    @Deprecated(
            message = "You should not need to use this. To issue updates from lower layers, see ChannelViewModel.",
            level = DeprecationLevel.ERROR
    )
    protected fun postState(viewState: VS) {
        _state.postValue(viewState)
    }
    // endregion

    //region Events
    /**
     * The [ActiveOnlySingleShotLiveData] collection dispatching one-time events from
     * the ViewModel to the Fragment or Activity.
     */
    private val viewEvents: MutableLiveDataCollection<OneShotEvent> =
            MutableLiveDataCollectionImpl(::ActiveOnlySingleShotLiveData)

    /**
     * The [LiveData] to be observed by the [RainbowCakeFragment] or
     * [RainbowCakeActivity] connected to this ViewModel to receive events.
     * This is a read-only view of the contained [ActiveOnlySingleShotLiveData]
     * collection.
     */
    @get:VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    val events: LiveDataCollection<OneShotEvent> = viewEvents

    /**
     * Posts a new event to the connected Fragment or Activity. Unlike
     * screen state changes, events are only delivered once, i.e.
     * they won't be re-delivered after a configuration change.
     *
     * Events posted with this method are dispatched immediately.
     *
     * If the Fragment or Activity is not currently in the foreground (in
     * a started state), the event will not be delivered at all.
     *
     * See also: [postQueuedEvent].
     */
    @Suppress("UsePropertyAccessSyntax")
    protected fun postEvent(event: OneShotEvent) {
        viewEvents.postValue(event)
    }


    /**
     * The [QueuedSingleShotLiveData] collection dispatching one-time events from
     * the ViewModel to the Fragment or Activity.
     */
    private val queuedViewEvents: MutableLiveDataCollection<QueuedOneShotEvent> =
            MutableLiveDataCollectionImpl(::QueuedSingleShotLiveData)

    /**
     * The [LiveData] to be observed by the [RainbowCakeFragment] or
     * [RainbowCakeActivity] connected to this ViewModel to receive
     * queued events. This is a read-only view of the contained
     * [QueuedSingleShotLiveData] collection.
     */
    @get:VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    val queuedEvents: LiveDataCollection<QueuedOneShotEvent> = queuedViewEvents

    /**
     * Posts a new event to the connected Fragment or Activity. Unlike
     * screen state changes, events are only delivered once, i.e.
     * they won't be re-delivered after a configuration change.
     *
     * Fragment or Activity isn't currently in the foreground (in a
     * started state), the event will be queued and dispatched later.
     *
     * Queueing is a best effort mechanism. Fragment and Activity
     * instances that are in the background but still in memory will
     * receive queued events when they become active again. Instances
     * that are completely destroyed will have their queues emptied.
     *
     * See also: [postEvent].
     */
    @Suppress("UsePropertyAccessSyntax")
    protected fun postQueuedEvent(event: QueuedOneShotEvent) {
        queuedViewEvents.postValue(event)
    }
    //endregion

    //region Coroutine support
    /**
     * An implementation of the [CoroutineScope] interface.
     *
     * Coroutines launched in this scope will get use the Main dispatcher (i.e.
     * start on the UI thread) and have a SupervisorJob as their parent. This job
     * is cancelled when the ViewModel is cleared, which also cancels all its
     * children coroutines.
     */
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
        log("ViewModel cleared, job cancelled")
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
        return coroutineScope.launch {
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
    //endregion

}
