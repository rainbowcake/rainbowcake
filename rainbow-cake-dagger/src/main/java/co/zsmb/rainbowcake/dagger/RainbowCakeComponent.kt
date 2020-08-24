package co.zsmb.rainbowcake.dagger

import androidx.lifecycle.ViewModelProvider

/**
 * A base interface for the application's actual Dagger component that's
 * capable of providing a [ViewModelProvider.Factory] instance.
 */
public interface RainbowCakeComponent {

    public fun viewModelFactory(): ViewModelProvider.Factory

}
