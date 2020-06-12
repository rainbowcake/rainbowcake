package co.zsmb.rainbowcake

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Calls the specified suspending block in the IO coroutine context, suspends until it completes,
 * and returns the result.
 *
 * For more details, see the [withContext] function that this function delegates to.
 */
@Suppress("DEPRECATION")
suspend inline fun <T> withIOContext(noinline block: suspend CoroutineScope.() -> T): T {
    return withContext(ioContext, block = block)
}

/**
 * The context used by the [withIOContext] function, [Dispatchers.IO] by default.
 *
 * Can be modified for testing purposes.
 */
@VisibleForTesting
@Deprecated(message = "ioContext should only be used in tests", level = DeprecationLevel.WARNING)
var ioContext: CoroutineContext = Dispatchers.IO
