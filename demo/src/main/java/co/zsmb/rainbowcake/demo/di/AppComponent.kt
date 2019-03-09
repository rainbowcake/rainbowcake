package co.zsmb.rainbowcake.demo.di

import co.zsmb.rainbowcake.di.BaseComponent
import co.zsmb.rainbowcake.di.BaseModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    BaseModule::class,
    ViewModelModule::class
])
interface AppComponent : BaseComponent
