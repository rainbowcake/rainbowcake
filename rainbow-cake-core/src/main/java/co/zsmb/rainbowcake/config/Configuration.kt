package co.zsmb.rainbowcake.config

import android.app.Application
import co.zsmb.rainbowcake.internal.config.RainbowCakeConfiguration

/**
 * The entry point of the Rainbow Cake configuration DSL.
 */
public inline fun Application.rainbowCake(actions: RainbowCakeConfigurator.() -> Unit) {
    RainbowCakeConfiguration.actions()
}
