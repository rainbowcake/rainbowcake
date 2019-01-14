package hu.autsoft.rainbowcake.demo.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import hu.autsoft.rainbowcake.demo.ui.bar.BarViewModel
import hu.autsoft.rainbowcake.demo.ui.example.ExampleViewModel
import hu.autsoft.rainbowcake.demo.ui.foo.FooViewModel
import hu.autsoft.rainbowcake.di.ViewModelKey

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExampleViewModel::class)
    abstract fun bindExampleViewModel(exampleViewModel: ExampleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FooViewModel::class)
    abstract fun bindFooViewModel(fooViewModel: FooViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BarViewModel::class)
    abstract fun bindBarViewModel(barViewModel: BarViewModel): ViewModel

}
