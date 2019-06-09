package co.zsmb.rainbowcake.demo.di

import android.arch.lifecycle.ViewModel
import co.zsmb.rainbowcake.demo.ui.bar.BarViewModel
import co.zsmb.rainbowcake.demo.ui.example.ExampleViewModel
import co.zsmb.rainbowcake.demo.ui.foo.FooViewModel
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.SharedVMPagerViewModel
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages.ScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @co.zsmb.rainbowcake.dagger.ViewModelKey(ExampleViewModel::class)
    abstract fun bindExampleViewModel(exampleViewModel: ExampleViewModel): ViewModel

    @Binds
    @IntoMap
    @co.zsmb.rainbowcake.dagger.ViewModelKey(FooViewModel::class)
    abstract fun bindFooViewModel(fooViewModel: FooViewModel): ViewModel

    @Binds
    @IntoMap
    @co.zsmb.rainbowcake.dagger.ViewModelKey(BarViewModel::class)
    abstract fun bindBarViewModel(barViewModel: BarViewModel): ViewModel

    @Binds
    @IntoMap
    @co.zsmb.rainbowcake.dagger.ViewModelKey(SharedVMPagerViewModel::class)
    abstract fun bindSharedVMPagerViewModel(sharedVMPagerViewModel: SharedVMPagerViewModel): ViewModel

    @Binds
    @IntoMap
    @co.zsmb.rainbowcake.dagger.ViewModelKey(ScreenViewModel::class)
    abstract fun bindScreenViewModel(screenViewModel: ScreenViewModel): ViewModel

}
