package co.zsmb.rainbowcake.demo

import co.zsmb.rainbowcake.BaseApplication
import co.zsmb.rainbowcake.config.LoggingOptions
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.demo.di.AppComponent
import co.zsmb.rainbowcake.demo.di.DaggerAppComponent
import co.zsmb.rainbowcake.timber.TIMBER
import timber.log.Timber

open class DemoApplication : BaseApplication() {

    override lateinit var injector: AppComponent

    override fun setupInjector() {
        injector = DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            isDebug = BuildConfig.DEBUG
            logger = LoggingOptions.TIMBER

            consumeExecuteExceptions = false
        }

        Timber.plant(Timber.DebugTree())
    }

}
