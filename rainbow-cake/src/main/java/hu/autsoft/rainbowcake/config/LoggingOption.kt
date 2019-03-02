package hu.autsoft.rainbowcake.config

import hu.autsoft.rainbowcake.logging.AndroidLogger
import hu.autsoft.rainbowcake.logging.BlankLogger
import hu.autsoft.rainbowcake.logging.Logger
import hu.autsoft.rainbowcake.logging.TimberLogger

interface LoggingOption {
    val logger: Logger
}

object None : LoggingOption {
    override val logger: Logger = BlankLogger
}

object Android : LoggingOption {
    override val logger: Logger = AndroidLogger
}

object Timber : LoggingOption {
    override val logger: Logger = TimberLogger
}

interface LoggingOptions

val LoggingOptions.Timber
    get() = hu.autsoft.rainbowcake.config.Timber

fun main() {
    rainbowCake {
        isDebug = false
        logging {
            logger = Timber
            enabled = true
        }
    }
}

private fun RainbowCakeConfigurator.logging(function: LoggingOptions.() -> Unit) {
    TODO("not implemented")
}

interface RainbowCakeConfigurator {
    var logger: LoggingOption
    var debugMode: Boolean
}

object RainbowCakeConfiguration : RainbowCakeConfigurator {
    override var debugMode: Boolean = false

    override var logger: LoggingOption = None
        set(value) {
            hu.autsoft.rainbowcake.logging.logger = value.logger
            field = value
        }
}

fun rainbowCake(actions: RainbowCakeConfigurator.() -> Unit) {
    RainbowCakeConfiguration.actions()
}
