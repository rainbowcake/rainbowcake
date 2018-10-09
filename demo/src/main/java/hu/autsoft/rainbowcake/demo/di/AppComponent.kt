package hu.autsoft.rainbowcake.demo.di

import dagger.Component
import hu.autsoft.rainbowcake.di.BaseComponent
import hu.autsoft.rainbowcake.di.BaseModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    BaseModule::class,
    ViewModelModule::class
])
interface AppComponent : BaseComponent
