package co.zsmb.rainbowcake.di

import co.zsmb.rainbowcake.base.InjectedActivity
import co.zsmb.rainbowcake.base.InjectedFragment
import co.zsmb.rainbowcake.base.RainbowCakeActivity
import co.zsmb.rainbowcake.base.RainbowCakeFragment

/**
 * A base interface for the application's actual Dagger component that's
 * capable of injecting the [RainbowCakeFragment] and [RainbowCakeActivity] classes via
 * their non-generic parents.
 */
interface RainbowCakeComponent {

    fun inject(injectedActivity: InjectedActivity)

    fun inject(injectedFragment: InjectedFragment)

}
