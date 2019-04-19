package co.zsmb.rainbowcake.channels

import kotlinx.coroutines.channels.Channel

/**
 * A helper interface describing a simple collection of [Channel] instances.
 * This is meant to be used by data sources that need to keep a list of the
 * channels that they emit events to.
 *
 * See [SynchronizedChannelCollection] for a ready-to-use, threadsafe implementation.
 */
interface ChannelCollection<T> {

    /**
     * Adds a channel to the collection
     *
     * @return true if this collection was empty before adding [channel]
     */
    fun addChannel(channel: Channel<T>): Boolean

    /**
     * Removes a channel from the collection
     *
     * @return true if the collection still has channels remaining, i.e.
     *         it's still not empty
     */
    fun removeChannel(channel: Channel<T>): Boolean

    /**
     * Performs an action on each channel in the collection
     */
    fun forEachChannel(actions: (Channel<T>) -> Unit)

    /**
     * Removes all channels from the collection
     *
     * This obviously leaves the collection empty, so the callbacks
     * supplying it should be cleared
     */
    fun clear()

}
