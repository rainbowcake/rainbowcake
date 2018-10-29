package hu.autsoft.rainbowcake.channels

import kotlinx.coroutines.channels.Channel

/**
 * An implementation of the [ChannelCollection] interface that may be
 * safely used from multiple threads.
 *
 * When implementing a data source that produces events for multiple
 * channels, this helper class may be subclassed by the data source,
 * its callback, or be delegated to by either of them.
 */
class SynchronizedChannelCollection<T> : ChannelCollection<T> {

    private val channels = mutableListOf<Channel<T>>()

    override fun forEachChannel(actions: (Channel<T>) -> Unit) {
        synchronized(channels) {
            channels.forEach(actions)
        }
    }

    override fun clear() {
        synchronized(channels) {
            channels.clear()
        }
    }

    override fun addChannel(channel: Channel<T>): Boolean {
        synchronized(channels) {
            channels += channel
            return channels.size == 1
        }
    }

    override fun removeChannel(channel: Channel<T>): Boolean {
        synchronized(channels) {
            channels -= channel
            return channels.isNotEmpty()
        }
    }

}
