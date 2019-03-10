package co.zsmb.rainbowcake

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object Dispatchers {

    /**
     * Android main thread
     */
    val UI = Dispatchers.Main

    /**
     * Threadpool for I/O operations
     */
    val IO = Dispatchers.IO

}

/**
 * Calls the specified suspending block in the IO coroutine context, suspends until it completes,
 * and returns the result.
 *
 * For more details, see the [withContext] function that this function delegates to.
 */
suspend fun <T> withIOContext(block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.IO, block = block)
