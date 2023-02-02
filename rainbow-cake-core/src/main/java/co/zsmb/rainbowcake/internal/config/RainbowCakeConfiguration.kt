package co.zsmb.rainbowcake.internal.config

import co.zsmb.rainbowcake.config.Logger
import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.RainbowCakeConfigurator

/**
 * Internal implementation of [RainbowCakeConfigurator].
 */
internal object RainbowCakeConfiguration : RainbowCakeConfigurator {

    override var isLoggable: Boolean = false

    override var logger: Logger = Loggers.NONE

    override var consumeExecuteExceptions: Boolean = true

}
