package co.zsmb.rainbowcake.dagger

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.zsmb.rainbowcake.base.RainbowCakeBottomSheetFragment
import co.zsmb.rainbowcake.base.RainbowCakeDialogFragment
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import co.zsmb.rainbowcake.base.ViewModelScope
import co.zsmb.rainbowcake.base.ViewModelScope.Activity
import co.zsmb.rainbowcake.base.ViewModelScope.Default
import co.zsmb.rainbowcake.base.ViewModelScope.ParentFragment

/**
 * Uses the ViewModelFactory from the [RainbowCakeComponent] inside the [RainbowCakeApplication] to
 * fetch the appropriate ViewModel instance for the Fragment.
 *
 * @param scope The scope that the ViewModel should be fetched from and exist in.
 *              See [ViewModelScope] for details.
 */
public inline fun <F : RainbowCakeFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModelFromFactory(
        scope: ViewModelScope = Default
): VM {
    return getFragmentViewModel(scope, viewModelFactory)
}

public inline fun <F : RainbowCakeDialogFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModelFromFactory(
        scope: ViewModelScope = Default
): VM {
    return getFragmentViewModel(scope, viewModelFactory)
}

public inline fun <F : RainbowCakeBottomSheetFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModelFromFactory(
        scope: ViewModelScope = Default
): VM {
    return getFragmentViewModel(scope, viewModelFactory)
}

@PublishedApi
internal inline val Fragment.viewModelFactory: ViewModelProvider.Factory
    get() = (context?.applicationContext as? RainbowCakeApplication)
            ?.injector
            ?.viewModelFactory()
            ?: throw IllegalStateException("The Dagger based getViewModelFromFactory function requires an Application that inherits from RainbowCakeApplication")

@PublishedApi
internal inline fun <VS, reified VM : RainbowCakeViewModel<VS>> Fragment.getFragmentViewModel(
        scope: ViewModelScope,
        viewModelFactory: ViewModelProvider.Factory
): VM {
    return when (scope) {
        Default -> {
            ViewModelProvider(this, viewModelFactory).get(VM::class.java)
        }
        is ParentFragment -> {
            val parentFragment = parentFragment ?: throw IllegalStateException("No parent Fragment")
            val key = scope.key
            if (key != null) {
                ViewModelProvider(parentFragment, viewModelFactory).get(key, VM::class.java)
            } else {
                ViewModelProvider(parentFragment, viewModelFactory).get(VM::class.java)
            }
        }
        is Activity -> {
            val key = scope.key
            if (key != null) {
                ViewModelProvider(requireActivity(), viewModelFactory).get(key, VM::class.java)
            } else {
                ViewModelProvider(requireActivity(), viewModelFactory).get(VM::class.java)
            }
        }
    }
}
