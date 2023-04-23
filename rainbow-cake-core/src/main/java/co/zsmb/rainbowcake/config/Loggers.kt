package co.zsmb.rainbowcake.config

import android.util.Log
import co.zsmb.rainbowcake.internal.logging.LogLevel

/**
 * The interface used by the framework's internals to perform logging.
 */
public interface Logger {
    public fun log(tag: String, message: String, logLevel: LogLevel = LogLevel.DEBUG)
    public fun logThrowable(tag: String, message: String, throwable: Throwable?, logLevel: LogLevel = LogLevel.DEBUG)
}

/**
 * Built-in [Logger] options.
 */
public object Loggers {

    /**
     * Logs nothing.
     */
    public object NONE : Logger {
        override fun log(tag: String, message: String, logLevel: LogLevel) {
            /* empty */
        }

        override fun logThrowable(tag: String, message: String, throwable: Throwable?, logLevel: LogLevel) {
            /* empty */
        }
    }

    /**
     * Logs to Logcat
     */
    public object ANDROID : Logger {
        override fun log(tag: String, message: String, logLevel: LogLevel) {
            when (logLevel) {
                LogLevel.VERBOSE -> Log.v(tag, message)
                LogLevel.DEBUG -> Log.d(tag, message)
                LogLevel.INFO -> Log.i(tag, message)
                LogLevel.WARN -> Log.w(tag, message)
                LogLevel.ERROR,
                LogLevel.ASSERT -> Log.e(tag, message)
                LogLevel.OFF -> {
                    // Do nothing
                }
            }
        }

        override fun logThrowable(tag: String, message: String, throwable: Throwable?, logLevel: LogLevel) {
            when (logLevel) {
                LogLevel.VERBOSE -> Log.v(tag, message, throwable)
                LogLevel.DEBUG -> Log.d(tag, message, throwable)
                LogLevel.INFO -> Log.i(tag, message, throwable)
                LogLevel.WARN -> Log.w(tag, message, throwable)
                LogLevel.ERROR,
                LogLevel.ASSERT -> Log.e(tag, message, throwable)
                LogLevel.OFF -> {
                    // Do nothing
                }
            }
        }
    }
}
