package co.zsmb.rainbowcake.koin

import co.zsmb.rainbowcake.base.RainbowCakeActivity
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 * Uses the global Koin instance to fetch the appropriate ViewModel instance for the Activity.
 */
inline fun <A : RainbowCakeActivity<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> A.getViewModelFromFactory(): VM {
    return getViewModel()
}
