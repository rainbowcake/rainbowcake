@file:Suppress("RedundantVisibilityModifier")

package co.zsmb.rainbowcake.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import co.zsmb.rainbowcake.internal.livedata.*

/**
 * A ViewModel base class that provides safe view state handling via [LiveData]
 * and one-time event support via [SingleShotLiveData].
 */
abstract class RainbowCakeViewModel<VS : Any>(initialState: VS) : ViewModel() {

    /**
     * The [MutableLiveData] instance actually containing the current view state.
     */
    private val _state: MutableLiveData<VS> = MutableLiveData()

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
            _state.value = value
        }

    /**
     * Sets the view state. This method may be called from a background thread.
     *
     * You should not need to use this, as [JobViewModel] provides an easy way to
     * launch background tasks and get back to the UI via coroutines. To issue
     * updates originating from lower layers, see [ChannelViewModel].
     */
    @Deprecated(
            message = "You should not need to use this. To issue updates from lower layers, see ChannelViewModel.",
            level = DeprecationLevel.WARNING
    )
    protected fun postState(viewState: VS) {
        _state.postValue(viewState)
    }


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
    internal val events: LiveDataCollection<OneShotEvent> = viewEvents

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
        viewEvents.setValue(event)
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
    internal val queuedEvents: LiveDataCollection<QueuedOneShotEvent> = queuedViewEvents

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
        queuedViewEvents.setValue(event)
    }

}
