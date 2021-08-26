package co.zsmb.rainbowcake.hilt

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.zsmb.rainbowcake.base.RainbowCakeBottomSheetDialogFragment
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import co.zsmb.rainbowcake.base.ViewModelScope

public inline fun <F : RainbowCakeFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModel(
    scope: ViewModelScope = ViewModelScope.Default
): VM {
    return getFragmentViewModel(scope)
}

public inline fun <F : RainbowCakeBottomSheetDialogFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModel(
    scope: ViewModelScope = ViewModelScope.Default
): VM {
    return getFragmentViewModel(scope)
}

@PublishedApi
internal inline fun <reified VM : RainbowCakeViewModel<VS>, VS> Fragment.getFragmentViewModel(scope: ViewModelScope): VM {
    return when (scope) {
        is ViewModelScope.Default -> {
            ViewModelProvider(this).get(VM::class.java)
        }
        is ViewModelScope.ParentFragment -> {
            val parentFragment = parentFragment ?: throw IllegalStateException("No parent Fragment")
            val key = scope.key
            if (key != null) {
                ViewModelProvider(parentFragment).get(key, VM::class.java)
            } else {
                ViewModelProvider(parentFragment).get(VM::class.java)
            }
        }
        is ViewModelScope.Activity -> {
            val key = scope.key
            if (key != null) {
                ViewModelProvider(requireActivity()).get(key, VM::class.java)
            } else {
                ViewModelProvider(requireActivity()).get(VM::class.java)
            }
        }
    }
}
