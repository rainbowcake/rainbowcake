package hu.autsoft.rainbowcake.logging

import android.util.Log
import hu.autsoft.rainbowcake.config.RainbowCakeConfiguration
import timber.log.Timber

interface Logger {
    fun log(tag: String, message: String)
}

@Suppress("unused")
internal inline fun <reified T> T.log(message: String) {
    if (RainbowCakeConfiguration.debugMode.not()) {
        return
    }
    logger.log(T::class.java.simpleName, message)
}

internal object TimberLogger : Logger {
    override fun log(tag: String, message: String) {
        Timber.tag(tag).d(message)
    }
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

internal var logger: Logger = TimberLogger
