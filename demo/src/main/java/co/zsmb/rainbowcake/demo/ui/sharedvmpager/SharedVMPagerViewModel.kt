package co.zsmb.rainbowcake.demo.ui.sharedvmpager

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class SharedVMPagerViewModel @Inject constructor(
        private val sharedVMPagerPresenter: SharedVMPagerPresenter
) : RainbowCakeViewModel<SharedVMPagerViewState>(SharedVMPagerViewState()) {

    fun load() = execute {
        viewState = SharedVMPagerViewState(sharedVMPagerPresenter.getData())
    }

}
