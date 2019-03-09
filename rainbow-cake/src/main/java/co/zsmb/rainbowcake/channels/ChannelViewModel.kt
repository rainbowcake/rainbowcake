package co.zsmb.rainbowcake.channels

import android.support.annotation.CallSuper
import co.zsmb.rainbowcake.base.JobViewModel
import co.zsmb.rainbowcake.internal.logging.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

/**
 * A ViewModel base class that in addition to providing state handling via [BaseViewModel]
 * and coroutine Job execution via [JobViewModel] also provides the ability to observe
 * updates from channels in a safe and concise way.
 *
 * ### Glossary
 *
 * ##### Channel
 *
 * A coroutine [Channel] emitting a stream of events. In this use case, they are used
 * to enable the ability of lower layers of the architecture (interactors, data
 * sources) to push events and data to the ViewModel without these events having
 * to be polled for.
 *
 * ##### Observer
 *
 * The callbacks that are hooked up to a channel and receive updates (new elements)
 * from it as they're emitted.
 *
 * ##### Observation
 *
 * In the context of this class, an *observation* is a collective name for a channel
 * and the callbacks connected to it via the [observe] methods. There's always a
 * one-to-one relation between *observations* in channels when using this class.
 *
 * When an *observation* is cancelled, the ViewModel stops receiving updates from
 * its channel **and** the channel itself is cancelled as well.
 *
 * This means that only a single [observe] call should ever be made to a channel.
 * Subsequently, any classes in lower layers should always return new channel
 * instances when one is requested from them, as they can not be reused due to
 * the way they are tied to the *observations*.
 *
 * Cancellation also happens the other way around: if a channel is cancelled on
 * the supplier's end, the *observation* will be cleaned up as well (after running
 * the appropriate callbacks of [observe]).
 */
@Suppress("MemberVisibilityCanBePrivate")
abstract class ChannelViewModel<VS : Any>(initialState: VS) : JobViewModel<VS>(initialState) {

    private val observations = hashMapOf<String, ReceiveChannel<*>>()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        observations.clear()
    }

    /**
     * Checks whether there's an existing *observation* with the given key.
     */
    protected fun isObserving(key: String): Boolean {
        return observations.containsKey(key)
    }

    /**
     * Removes the *observation* with the given key if it exists (removes the observer
     * and cancels the underlying channel). This method is safe to call without
     * checking whether the *observation* exists first.
     *
     * @return true if there was an *observation* to remove
     */
    protected fun removeObserver(key: String): Boolean {
        val channel = observations.remove(key)
        channel?.cancel()
        return channel != null
    }

    /**
     * Start a unique channel *observation* with a given [key]. Only one *observation* can
     * be active with a key at a time.
     *
     * @param key The key that uniquely identifies this channel *observation*.
     * @param replaceExisting Whether to replace the existing *observation* for the given
     *                        [key] with the one being created now, if it exists. If this
     *                        parameter is false and the [key] is already taken, this call
     *                        is a no-op and terminates silently. The channel that was to
     *                        be observed will be cancelled.
     *                        Note that this is wasteful because getting this channel
     *                        might have already activated callbacks and other mechanisms
     *                        in underlying data sources, which will now be shut down. If
     *                        this activation and shutdown has significant costs in a
     *                        specific use case, [isObserving] should be used to check if
     *                        there's an active *observation* first.
     * @param onClosed A callback for when the channel is closed. This may happen because
     *                 the data that was being supplied has ran out, because an exception
     *                 happened somewhere along the way, or because the ViewModel was
     *                 cleared and therefore cancelled the underlying channel.
     * @param onCancelled Similar to [onClosed], but will only be called if the channel
     *                    has terminated exceptionally due to an Exception being thrown
     *                    in the lower layers. When this happens, [onClosed] will still
     *                    be invoked, but this callback runs first.
     * @param onElement The main callback of the function, will be called for every
     *                  element emitted by the channel being observed.
     */
    protected suspend fun <T> ReceiveChannel<T>.observe(
            key: String,
            replaceExisting: Boolean = false,
            onClosed: suspend () -> Unit = {},
            onCancelled: suspend (Exception) -> Unit = {},
            onElement: suspend (T) -> Unit
    ) {
        if (observations.contains(key)) {
            if (!replaceExisting) {
                log("Key $key already in use")
                this.cancel()
                return
            } else {
                removeObserver(key)
            }
        }

        observations.put(key, this)
        log("Key $key taken")

        try {
            observe(onClosed, onCancelled, onElement)
        } finally {
            observations.remove(key)
            log("Key $key freed up")
        }
    }

    /**
     * Start a non-unique channel *observation*. Calling this function always creates an
     * *observation*, and multiple identical calls will result in multiple active
     * *observations*. Note that these calls still have to be made on different channel
     * instances, as channels in *observations* can not be shared.
     *
     * @param onClosed A callback for when the channel is closed. This may be because
     *                 the data that was being supplied has ran out, because an exception
     *                 happened somewhere along the way, or because the ViewModel was
     *                 cleared and therefore cancelled the underlying channel.
     * @param onCancelled Similar to [onClosed], but will only be called if the channel
     *                    has terminated exceptionally due to an Exception being thrown
     *                    in the lower layers. When this happens, [onClosed] will still
     *                    be invoked, but this callback runs first.
     * @param onElement The main callback of the function, will be called for every
     *                  element emitted by the channel being observed.
     */
    protected suspend fun <T> ReceiveChannel<T>.observe(
            onClosed: suspend () -> Unit = {},
            onCancelled: suspend (Exception) -> Unit = {},
            onElement: suspend (T) -> Unit
    ) {
        try {
            for (element in this) {
                onElement(element)
            }
        } catch (e: CancellationException) {
            log("Observer coroutine cancelled")
            log(e)
            this.cancel()
            log("Channel killed")
        } catch (e: Exception) {
            log("Channel cancelled from down below")
            log(e)
            onCancelled(e)
        } finally {
            log("Channel under observation was closed")
            onClosed()
        }
    }

}
