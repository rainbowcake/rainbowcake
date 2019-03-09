package co.zsmb.rainbowcake

import android.app.Application
import android.support.annotation.CallSuper
import co.zsmb.rainbowcake.base.InjectedActivity
import co.zsmb.rainbowcake.base.InjectedFragment
import co.zsmb.rainbowcake.di.BaseComponent

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
    override fun onCreate() {
        super.onCreate()
        setupInjector()
    }

    /**
     * Creates a Dagger injector and sets the [injector] property
     */
    protected abstract fun setupInjector()

}
