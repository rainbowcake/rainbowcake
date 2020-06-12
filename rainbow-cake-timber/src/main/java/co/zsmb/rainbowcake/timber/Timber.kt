package co.zsmb.rainbowcake.timber

import co.zsmb.rainbowcake.config.Logger
import co.zsmb.rainbowcake.config.Loggers
import timber.log.Timber

private object Timber : Logger {
    override fun log(tag: String, message: String) {
        Timber.tag(tag).d(message)
    }
}

val Loggers.TIMBER: Logger
    get() = Timber
