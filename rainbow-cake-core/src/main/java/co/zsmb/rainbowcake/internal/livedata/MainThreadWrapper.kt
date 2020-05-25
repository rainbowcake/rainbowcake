package co.zsmb.rainbowcake.internal.livedata

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

object MainThreadWrapper {
    @Suppress("ObjectPropertyName")
    private var _executor: Executor? = null

    var executor: Executor
        get() {
            if (_executor == null) {
                _executor = DefaultExecutor()
            }
            return _executor!!
        }
        set(value) {
            _executor = value
        }

    fun resetExecutor() {
        _executor = null
    }

    private class DefaultExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            handler.post { command.run() }
        }
    }
}
