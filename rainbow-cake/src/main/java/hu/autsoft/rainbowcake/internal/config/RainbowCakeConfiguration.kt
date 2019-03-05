package hu.autsoft.rainbowcake.internal.config

import hu.autsoft.rainbowcake.config.LoggingOption
import hu.autsoft.rainbowcake.config.LoggingOptions
import hu.autsoft.rainbowcake.config.RainbowCakeConfigurator

internal object RainbowCakeConfiguration : RainbowCakeConfigurator {

    override var isDebug: Boolean = false

    override var logger: LoggingOption = LoggingOptions.NONE

    override var consumeExecuteExceptions: Boolean = true

}
