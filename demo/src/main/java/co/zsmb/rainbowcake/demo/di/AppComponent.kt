package co.zsmb.rainbowcake.demo.di

import co.zsmb.rainbowcake.di.RainbowCakeComponent
import co.zsmb.rainbowcake.di.RainbowCakeModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    RainbowCakeModule::class,
    ViewModelModule::class
])
interface AppComponent : RainbowCakeComponent
