package hu.autsoft.rainbowcake.timber

import hu.autsoft.rainbowcake.config.Logger
import hu.autsoft.rainbowcake.config.LoggingOption
import hu.autsoft.rainbowcake.config.LoggingOptions
import timber.log.Timber

private object Timber : LoggingOption {
    override val logger: Logger = TimberLogger
}

val LoggingOptions.TIMBER: LoggingOption
    get() = hu.autsoft.rainbowcake.timber.Timber

internal object TimberLogger : Logger {
    override fun log(tag: String, message: String) {
        Timber.tag(tag).d(message)
        Timber.d(Exception())
    }
}
