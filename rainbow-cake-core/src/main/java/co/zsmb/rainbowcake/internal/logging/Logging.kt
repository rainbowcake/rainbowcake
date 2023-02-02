package co.zsmb.rainbowcake.internal.logging

import co.zsmb.rainbowcake.config.isProd
import co.zsmb.rainbowcake.internal.config.RainbowCakeConfiguration
import java.io.PrintWriter
import java.io.StringWriter

@Suppress("unused")
internal fun log(tag: String, message: String, throwable: Throwable? = null, logLevel: LogLevel = LogLevel.DEBUG) {
    if (RainbowCakeConfiguration.isProd) {
        return
    }
    if (throwable == null) {
        RainbowCakeConfiguration.logger.log(tag, message, logLevel)
    } else {
        RainbowCakeConfiguration.logger.logThrowable(tag, message, throwable, logLevel)
    }
}

/**
 * Allows logging errors without message
 */
internal inline fun <reified T> T.logError(tag: String, e: Throwable, logLevel: LogLevel = LogLevel.DEBUG) {
    if (RainbowCakeConfiguration.isProd) {
        return
    }
    log(tag, getStacktraceString(e), e, logLevel)
}

private fun getStacktraceString(e: Throwable): String {
    // Implementation from Timber
    val sw = StringWriter(256)
    val pw = PrintWriter(sw, false)
    e.printStackTrace(pw)
    pw.flush()
    return sw.toString()
}
