package hu.autsoft.rainbowcake

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.withContext
import kotlinx.coroutines.experimental.android.UI as AndroidUI

object Contexts {

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
suspend fun <T> withIOContext(block: suspend CoroutineScope.() -> T): T = withContext(Contexts.IO, block = block)
