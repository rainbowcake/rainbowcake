package hu.autsoft.rainbowcake.di

import hu.autsoft.rainbowcake.base.BaseActivity
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.InjectedActivity
import hu.autsoft.rainbowcake.base.InjectedFragment


/**
 * A base interface for the application's actual Dagger component that's
 * capable of injecting the [BaseFragment] and [BaseActivity] classes via
 * their non-generic parents.
 */
interface BaseComponent {

    fun inject(baseActivity: InjectedActivity)

    fun inject(baseFragment: InjectedFragment)

}
