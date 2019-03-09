package co.zsmb.rainbowcake.config

import co.zsmb.rainbowcake.internal.config.RainbowCakeConfiguration

fun rainbowCake(actions: RainbowCakeConfigurator.() -> Unit) {
    RainbowCakeConfiguration.actions()
}
