package hu.autsoft.rainbowcake.channels

import hu.autsoft.rainbowcake.Contexts
import kotlinx.coroutines.experimental.CompletionHandler
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.ProducerScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce

/**
 * Launches a new coroutine in the IO coroutine context (and in [GlobalScope]) to produce a stream
 * of values by sending them to a channel, and returns the created [ReceiveChannel].
 *
 * For more details, see the [GlobalScope.produce] function that this function delegates to.
 */
fun <T> produceInIOContext(
        capacity: Int = 0,
        onCompletion: CompletionHandler? = null,
        block: suspend ProducerScope<T>.() -> Unit
): ReceiveChannel<T> {
    return GlobalScope.produce(
            context = Contexts.IO,
            capacity = capacity,
            onCompletion = onCompletion,
            block = block
    )
}
