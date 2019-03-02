package hu.autsoft.rainbowcake.config

import hu.autsoft.rainbowcake.internal.logging.AndroidLogger
import hu.autsoft.rainbowcake.internal.logging.BlankLogger

interface LoggingOption {
    val logger: Logger
}

interface Logger {
    fun log(tag: String, message: String)
}

object LoggingOptions {

    object NONE : LoggingOption {
        override val logger: Logger = BlankLogger
    }

    object ANDROID : LoggingOption {
        override val logger: Logger = AndroidLogger
    }

}
