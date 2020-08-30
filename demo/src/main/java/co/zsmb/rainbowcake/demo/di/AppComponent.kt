package co.zsmb.rainbowcake.demo.di

import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import co.zsmb.rainbowcake.demo.ui.mapper.dagger.RainbowCakeMapperModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RainbowCakeModule::class,
    ViewModelModule::class,
    RainbowCakeMapperModule::class
])
interface AppComponent : RainbowCakeComponent
