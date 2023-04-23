package co.zsmb.rainbowcake.hiltdemo

import android.app.Application
import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.timber.TIMBER
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HiltDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            logger = Loggers.TIMBER
            isDebug = BuildConfig.DEBUG
            consumeExecuteExceptions = !BuildConfig.DEBUG
        }

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
