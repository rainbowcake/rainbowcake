package co.zsmb.rainbowcake.config

import co.zsmb.rainbowcake.internal.config.RainbowCakeConfiguration

/**
 * The entry point of the Rainbow Cake configuration DSL.
 */
fun rainbowCake(actions: RainbowCakeConfigurator.() -> Unit) {
    RainbowCakeConfiguration.actions()
}
