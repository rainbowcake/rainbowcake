package co.zsmb.rainbowcake.internal.config

import co.zsmb.rainbowcake.config.LoggingOption
import co.zsmb.rainbowcake.config.LoggingOptions
import co.zsmb.rainbowcake.config.RainbowCakeConfigurator

internal object RainbowCakeConfiguration : RainbowCakeConfigurator {

    override var isDebug: Boolean = false

    override var logger: LoggingOption = LoggingOptions.NONE

    override var consumeExecuteExceptions: Boolean = true

}
