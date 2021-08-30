package co.zsmb.rainbowcake.hilt

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.zsmb.rainbowcake.base.RainbowCakeBottomSheetDialogFragment
import co.zsmb.rainbowcake.base.RainbowCakeDialogFragment
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import co.zsmb.rainbowcake.base.ViewModelScope
import co.zsmb.rainbowcake.base.ViewModelScope.Activity
import co.zsmb.rainbowcake.base.ViewModelScope.Default
import co.zsmb.rainbowcake.base.ViewModelScope.ParentFragment

/**
 * Uses the HiltViewModelFactory provided by Hilt to
 * fetch the appropriate ViewModel instance for the Fragment.

 * @param scope The scope that the ViewModel should be fetched from and exist in.
 *              See [ViewModelScope] for details.
 */
public inline fun <F : RainbowCakeFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModel(
    scope: ViewModelScope = Default
): VM {
    return getFragmentViewModel(scope)
}

/**
 * Uses the HiltViewModelFactory provided by Hilt to
 * fetch the appropriate ViewModel instance for the Fragment.

 * @param scope The scope that the ViewModel should be fetched from and exist in.
 *              See [ViewModelScope] for details.
 */
public inline fun <F : RainbowCakeDialogFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModel(
    scope: ViewModelScope = Default
): VM {
    return getFragmentViewModel(scope)
}

/**
 * Uses the HiltViewModelFactory provided by Hilt to
 * fetch the appropriate ViewModel instance for the Fragment.

 * @param scope The scope that the ViewModel should be fetched from and exist in.
 *              See [ViewModelScope] for details.
 */
public inline fun <F : RainbowCakeBottomSheetDialogFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModel(
    scope: ViewModelScope = Default
): VM {
    return getFragmentViewModel(scope)
}

@PublishedApi
internal inline fun <reified VM : RainbowCakeViewModel<VS>, VS> Fragment.getFragmentViewModel(scope: ViewModelScope): VM {
    return when (scope) {
        is Default -> {
            ViewModelProvider(this).get(VM::class.java)
        }
        is ParentFragment -> {
            val parentFragment = parentFragment ?: throw IllegalStateException("No parent Fragment")
            val key = scope.key
            if (key != null) {
                ViewModelProvider(parentFragment).get(key, VM::class.java)
            } else {
                ViewModelProvider(parentFragment).get(VM::class.java)
            }
        }
        is Activity -> {
            val key = scope.key
            if (key != null) {
                ViewModelProvider(requireActivity()).get(key, VM::class.java)
            } else {
                ViewModelProvider(requireActivity()).get(VM::class.java)
            }
        }
    }
}
