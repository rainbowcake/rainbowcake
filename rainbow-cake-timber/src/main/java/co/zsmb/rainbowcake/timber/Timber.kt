package co.zsmb.rainbowcake.timber

import co.zsmb.rainbowcake.config.Logger
import co.zsmb.rainbowcake.config.Loggers

private object Timber : Logger {
    override fun log(tag: String, message: String) {
        timber.log.Timber.tag(tag).d(message)
    }
}

public val Loggers.TIMBER: Logger
    get() = Timber
