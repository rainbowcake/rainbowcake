package co.zsmb.rainbowcake.internal.logging

import co.zsmb.rainbowcake.internal.config.RainbowCakeConfiguration
import java.io.PrintWriter
import java.io.StringWriter

@Suppress("unused")
internal inline fun <reified T> T.log(message: String) {
    if (RainbowCakeConfiguration.isDebug.not()) {
        return
    }
    RainbowCakeConfiguration.logger.logger.log(T::class.java.simpleName, message)
}

internal inline fun <reified T> T.log(e: Throwable) {
    if (RainbowCakeConfiguration.isDebug.not()) {
        return
    }

    log(getStacktraceString(e))
}

private fun getStacktraceString(e: Throwable): String {
    // Implementation taken from Timber
    val sw = StringWriter(256)
    val pw = PrintWriter(sw, false)
    e.printStackTrace(pw)
    pw.flush()
    return sw.toString()
}
