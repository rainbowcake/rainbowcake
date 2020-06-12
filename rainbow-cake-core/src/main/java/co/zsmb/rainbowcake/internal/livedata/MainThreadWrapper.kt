@file:Suppress("RedundantVisibilityModifier")

package co.zsmb.rainbowcake.internal.livedata

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PACKAGE_PRIVATE
import java.util.concurrent.Executor

/**
 * A wrapper for the Android main thread, used internally by the library's
 * custom LiveData implementations. Visible for testing purposes only.
 * Consider using the LiveDataTestRule class of rainbow-cake-test if you
 * need to replace the main thread instead of directly .
 */
@VisibleForTesting(otherwise = PACKAGE_PRIVATE)
object MainThreadWrapper {
    @Suppress("ObjectPropertyName")
    private var _executor: Executor? = null

    /**
     * The executor used for running code on the main thread.
     */
    public var executor: Executor
        get() {
            if (_executor == null) {
                _executor = MainThreadExecutor()
            }
            return _executor!!
        }
        set(value) {
            _executor = value
        }

    /**
     * Reset the internal executor to the real Android main thread.
     */
    public fun resetExecutor() {
        _executor = null
    }

    private class MainThreadExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            handler.post { command.run() }
        }
    }
}
