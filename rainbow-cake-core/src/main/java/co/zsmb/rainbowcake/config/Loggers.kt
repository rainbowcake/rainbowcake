package co.zsmb.rainbowcake.config

import android.util.Log

/**
 * The interface used by the framework's internals to perform logging.
 */
public interface Logger {
    public fun log(tag: String, message: String)
}

/**
 * Built-in [Logger] options.
 */
public object Loggers {

    /**
     * Logs nothing.
     */
    public object NONE : Logger {
        override fun log(tag: String, message: String) {
            /* empty */
        }
    }

    /**
     * Logs to Logcat using [Log.d].
     */
    public object ANDROID : Logger {
        override fun log(tag: String, message: String) {
            Log.d(tag, message)
        }
    }

}
