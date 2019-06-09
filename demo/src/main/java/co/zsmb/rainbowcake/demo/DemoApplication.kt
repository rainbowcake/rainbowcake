package co.zsmb.rainbowcake.demo

import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.demo.di.AppComponent
import co.zsmb.rainbowcake.demo.di.DaggerAppComponent
import co.zsmb.rainbowcake.demo.ui.koin.KoinPresenter
import co.zsmb.rainbowcake.demo.ui.koin.KoinViewModel
import co.zsmb.rainbowcake.timber.TIMBER
import org.koin.core.context.startKoin
import org.koin.dsl.module
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
            logger = Loggers.TIMBER

            consumeExecuteExceptions = false
        }

        Timber.plant(Timber.DebugTree())

        val viewModelModule = module {
            factory { KoinPresenter() }
            factory { KoinViewModel(get()) }
        }

        startKoin {
            modules(viewModelModule)
        }
    }

}
