package co.zsmb.rainbowcake.timber

import co.zsmb.rainbowcake.config.Logger
import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.internal.logging.LogLevel

private object Timber : Logger {

    override fun log(tag: String, message: String, logLevel: LogLevel) {
        timber.log.Timber.tag(tag).log(logLevel.priority, message)
    }
}

public val Loggers.TIMBER: Logger
    get() = Timber
