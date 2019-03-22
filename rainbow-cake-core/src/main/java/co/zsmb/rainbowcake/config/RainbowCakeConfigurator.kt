package co.zsmb.rainbowcake.config

import android.util.Log
import co.zsmb.rainbowcake.base.JobViewModel

/**
 * List of configurable framework features.
 */
interface RainbowCakeConfigurator {

    /**
     * Whether the application is currently in a Debug build.
     * Should almost always take the value of BuildConfig.DEBUG.
     *
     * Effects:
     * - Logging is disabled in non-debug mode, regardless of the [logger] set.
     *
     * Default value: false.
     */
    var isDebug: Boolean

    /**
     * The logging method to use for the library's internals.
     *
     * Built in options:
     * - [Loggers.NONE]: Doesn't log messages.
     * - [Loggers.ANDROID]: Uses [Log.d] to log messages.
     *
     * Default value: [Loggers.NONE].
     */
    var logger: Logger

    /**
     * Whether [JobViewModel.execute] should consume and log any uncaught
     * exceptions from coroutines, or let them crash the application.
     *
     * Default value: true.
     */
    var consumeExecuteExceptions: Boolean

}
