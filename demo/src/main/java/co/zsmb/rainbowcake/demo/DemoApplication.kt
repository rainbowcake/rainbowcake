package co.zsmb.rainbowcake.demo

import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.demo.di.AppComponent
import co.zsmb.rainbowcake.demo.di.DaggerAppComponent
import co.zsmb.rainbowcake.demo.ui.UIModule
import co.zsmb.rainbowcake.timber.TIMBER
import org.koin.core.context.startKoin
import timber.log.Timber

open class DemoApplication : RainbowCakeApplication() {

    override lateinit var injector: AppComponent

    override fun setupInjector() {
        injector = DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            isDebug = BuildConfig.DEBUG
            isLoggable = BuildConfig.DEBUG
            logger = Loggers.TIMBER

            consumeExecuteExceptions = false
        }

        Timber.plant(Timber.DebugTree())

        startKoin {
            modules(UIModule)
        }
    }
}
