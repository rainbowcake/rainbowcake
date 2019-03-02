package hu.autsoft.rainbowcake.config

import hu.autsoft.rainbowcake.internal.config.RainbowCakeConfiguration

fun rainbowCake(actions: RainbowCakeConfigurator.() -> Unit) {
    RainbowCakeConfiguration.actions()
}
