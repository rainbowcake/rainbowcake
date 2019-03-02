package hu.autsoft.rainbowcake.config

import timber.log.Timber

private object Timber : LoggingOption {
    override val logger: Logger = TimberLogger
}

val LoggingOptions.Timber: LoggingOption
    get() = hu.autsoft.rainbowcake.config.Timber

internal object TimberLogger : Logger {
    override fun log(tag: String, message: String) {
        Timber.tag(tag).d(message)
    }
}
