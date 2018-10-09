package hu.autsoft.rainbowcake

import android.app.Application
import android.content.Context
import android.support.annotation.CallSuper
import android.support.multidex.MultiDex
import hu.autsoft.rainbowcake.base.InjectedActivity
import hu.autsoft.rainbowcake.base.InjectedFragment
import hu.autsoft.rainbowcake.di.BaseComponent
import timber.log.Timber

/**
 * Base class for applications built on this architecture, primarily
 * used for DI integration
 */
abstract class BaseApplication : Application() {

    /**
     * The injector used by [InjectedFragment] and [InjectedActivity]
     */
    abstract val injector: BaseComponent

    @CallSuper
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        setupInjector()
        Timber.plant(Timber.DebugTree())
    }

    /**
     * Creates a Dagger injector and sets the [injector] property
     */
    protected abstract fun setupInjector()

}
