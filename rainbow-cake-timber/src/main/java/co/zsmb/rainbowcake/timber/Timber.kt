package co.zsmb.rainbowcake.timber

import co.zsmb.rainbowcake.config.Logger
import co.zsmb.rainbowcake.config.LoggingOption
import co.zsmb.rainbowcake.config.LoggingOptions
import timber.log.Timber

private object Timber : LoggingOption {
    override val logger: Logger = TimberLogger
}

val LoggingOptions.TIMBER: LoggingOption
    get() = co.zsmb.rainbowcake.timber.Timber

private object TimberLogger : Logger {
    override fun log(tag: String, message: String) {
        Timber.tag(tag).d(message)
    }
}
