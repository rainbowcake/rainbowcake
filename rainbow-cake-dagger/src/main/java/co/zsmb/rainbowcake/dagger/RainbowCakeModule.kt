package co.zsmb.rainbowcake.dagger

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * A module collecting all of the injectable dependencies provided by the architecture.
 */
@Suppress("unused")
@Module
abstract class RainbowCakeModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
