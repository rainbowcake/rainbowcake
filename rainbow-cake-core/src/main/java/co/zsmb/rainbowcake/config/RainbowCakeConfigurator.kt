package co.zsmb.rainbowcake.config

import android.util.Log
import co.zsmb.rainbowcake.base.RainbowCakeViewModel

/**
 * List of configurable framework features.
 */
public interface RainbowCakeConfigurator {

    /**
     * Whether the application is currently in a Debug build.
     * Should almost always take the value of BuildConfig.DEBUG.
     *
     * Effects:
     * - Logging is disabled in non-debug mode, regardless of the [logger] set.
     *
     * Default value: false.
     */
    @Deprecated(
        message = "This configuration will be removed. Use isLoggable instead.",
        replaceWith = ReplaceWith(
            expression = "isLoggable",
            imports = ["co.zsmb.rainbowcake.config.RainbowCakeConfigurator"]
        )
    )
    public var isDebug: Boolean

    /**
     * Whether the application is currently loggable or not.
     * Should almost always take the value of BuildConfig.DEBUG.
     *
     * Effects:
     * - Logging is disabled in non-debug mode, regardless of the [logger] set.
     *
     * Default value: false.
     */
    public var isLoggable: Boolean

    /**
     * The logging method to use for the library's internals.
     *
     * Built in options:
     * - [Loggers.NONE]: Doesn't log messages.
     * - [Loggers.ANDROID]: Uses [Log.d] to log messages.
     *
     * Default value: [Loggers.NONE].
     */
    public var logger: Logger

    /**
     * Whether [RainbowCakeViewModel.execute] should consume and log any uncaught
     * exceptions from coroutines, or let them crash the application.
     *
     * Default value: true.
     */
    public var consumeExecuteExceptions: Boolean
}

/**
 * Whether the application is running in production.
 */
internal val RainbowCakeConfigurator.isProd
    get() = !isDebug

/**
 * Whether the application is not allowed to send log.
 *
 * Note: [isProd] is used for now to ensure retro-compatibility for release builds !
 */
internal val RainbowCakeConfigurator.isNotLoggable
    get() = !isLoggable || isProd
