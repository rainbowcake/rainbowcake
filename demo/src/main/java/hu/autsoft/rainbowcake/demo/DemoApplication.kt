package hu.autsoft.rainbowcake.demo

import hu.autsoft.rainbowcake.BaseApplication
import hu.autsoft.rainbowcake.config.LoggingOptions
import hu.autsoft.rainbowcake.config.Timber
import hu.autsoft.rainbowcake.config.rainbowCake
import hu.autsoft.rainbowcake.demo.di.AppComponent
import hu.autsoft.rainbowcake.demo.di.DaggerAppComponent

open class DemoApplication : BaseApplication() {

    override lateinit var injector: AppComponent

    override fun setupInjector() {
        injector = DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            isDebug = false
            logger = LoggingOptions.Timber
        }
    }

}
