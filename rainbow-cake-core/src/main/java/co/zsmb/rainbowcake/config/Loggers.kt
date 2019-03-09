package co.zsmb.rainbowcake.config

import android.util.Log

/**
 * The interface used by the framework's internals to perform logging.
 */
interface Logger {
    fun log(tag: String, message: String)
}

/**
 * Built-in [Logger] options.
 */
object Loggers {

    /**
     * Logs nothing.
     */
    object NONE : Logger {
        override fun log(tag: String, message: String) {
            /* empty */
        }
    }

    /**
     * Logs to Logcat using [Log.d].
     */
    object ANDROID : Logger {
        override fun log(tag: String, message: String) {
            Log.d(tag, message)
        }
    }

}
