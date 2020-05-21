package co.zsmb.rainbowcake.dagger

import androidx.lifecycle.ViewModelProviders
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
inline fun <F : RainbowCakeFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModelFromFactory(
        scope: ViewModelScope = ViewModelScope.Default
): VM {
    val viewModelFactory = (getContext()?.applicationContext as? RainbowCakeApplication)
            ?.injector
            ?.viewModelFactory()
            ?: throw IllegalStateException("RainbowCakeFragment should not be used without an Application that inherits from RainbowCakeApplication")

    return when (scope) {
        ViewModelScope.Default -> {
            ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
        }
        is ViewModelScope.ParentFragment -> {
            val parentFragment = getParentFragment()
                    ?: throw IllegalStateException("No parent Fragment")
            val key = scope.key
            if (key != null) {
                ViewModelProviders.of(parentFragment, viewModelFactory).get(key, VM::class.java)
            } else {
                ViewModelProviders.of(parentFragment, viewModelFactory).get(VM::class.java)
            }
        }
        is ViewModelScope.Activity -> {
            val key = scope.key
            if (key != null) {
                ViewModelProviders.of(requireActivity(), viewModelFactory).get(key, VM::class.java)
            } else {
                ViewModelProviders.of(requireActivity(), viewModelFactory).get(VM::class.java)
            }
        }
    }
}
