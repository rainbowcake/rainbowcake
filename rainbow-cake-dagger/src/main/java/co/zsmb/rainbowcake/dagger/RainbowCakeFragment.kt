package co.zsmb.rainbowcake.dagger

import androidx.lifecycle.ViewModelProvider
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import co.zsmb.rainbowcake.base.ViewModelScope


/**
 * Uses the ViewModelFactory from the [RainbowCakeComponent] inside the [RainbowCakeApplication] to
 * fetch the appropriate ViewModel instance for the Fragment.
 *
 * @param scope The scope that the ViewModel should be fetched from and exist in.
 *              See [ViewModelScope] for details.
 */
public inline fun <F : RainbowCakeFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModelFromFactory(
        scope: ViewModelScope = ViewModelScope.Default
): VM {
    val viewModelFactory = (getContext()?.applicationContext as? RainbowCakeApplication)
            ?.injector
            ?.viewModelFactory()
            ?: throw IllegalStateException("RainbowCakeFragment should not be used without an Application that inherits from RainbowCakeApplication")

    return when (scope) {
        ViewModelScope.Default -> {
            ViewModelProvider(this, viewModelFactory).get(VM::class.java)
        }
        is ViewModelScope.ParentFragment -> {
            val parentFragment = getParentFragment()
                    ?: throw IllegalStateException("No parent Fragment")
            val key = scope.key
            if (key != null) {
                ViewModelProvider(parentFragment, viewModelFactory).get(key, VM::class.java)
            } else {
                ViewModelProvider(parentFragment, viewModelFactory).get(VM::class.java)
            }
        }
        is ViewModelScope.Activity -> {
            val key = scope.key
            if (key != null) {
                ViewModelProvider(requireActivity(), viewModelFactory).get(key, VM::class.java)
            } else {
                ViewModelProvider(requireActivity(), viewModelFactory).get(VM::class.java)
            }
        }
    }
}
