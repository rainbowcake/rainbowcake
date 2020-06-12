package co.zsmb.rainbowcake.dagger

import android.app.Application
import androidx.annotation.CallSuper

/**
 * Base class for applications built on this architecture, primarily
 * used for DI integration
 */
abstract class RainbowCakeApplication : Application() {

    /**
     * The injector used by the [getViewModelFromFactory] methods.
     */
    abstract val injector: RainbowCakeComponent

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        setupInjector()
    }

    /**
     * This method should create a Dagger injector set the [injector] property.
     */
    protected abstract fun setupInjector()

}
