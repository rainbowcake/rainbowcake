package co.zsmb.rainbowcake.channels

import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

/**
 * Launches a new coroutine in the IO coroutine context (and in [GlobalScope]) to produce a stream
 * of values by sending them to a channel, and returns the created [ReceiveChannel].
 *
 * For more details, see the [GlobalScope.produce] function that this function delegates to.
 */
@Suppress("EXPERIMENTAL_API_USAGE")
@UseExperimental(InternalCoroutinesApi::class)
fun <T> produceInIOContext(
        capacity: Int = 0,
        onCompletion: CompletionHandler? = null,
        block: suspend ProducerScope<T>.() -> Unit
): ReceiveChannel<T> {
    return GlobalScope.produce(
            context = Dispatchers.IO,
            capacity = capacity,
            onCompletion = onCompletion,
            block = block
    )
}
