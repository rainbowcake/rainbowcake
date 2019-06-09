package co.zsmb.rainbowcake.demo.di

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    co.zsmb.rainbowcake.dagger.RainbowCakeModule::class,
    ViewModelModule::class
])
interface AppComponent : co.zsmb.rainbowcake.dagger.RainbowCakeComponent
