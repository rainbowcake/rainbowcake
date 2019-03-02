package hu.autsoft.rainbowcake.internal.config

import android.util.Log
import hu.autsoft.rainbowcake.config.Logger

@Suppress("unused")
internal inline fun <reified T> T.log(message: String) {
    if (RainbowCakeConfiguration.isDebug.not()) {
        return
    }
    RainbowCakeConfiguration.logger.logger.log(T::class.java.simpleName, message)
}

internal object BlankLogger : Logger {
    override fun log(tag: String, message: String) {
    }
}

internal object AndroidLogger : Logger {
    override fun log(tag: String, message: String) {
        Log.d(tag, message)
    }
}
