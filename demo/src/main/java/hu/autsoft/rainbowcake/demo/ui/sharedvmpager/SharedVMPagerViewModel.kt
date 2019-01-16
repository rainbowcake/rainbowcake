package hu.autsoft.rainbowcake.demo.ui.sharedvmpager

import hu.autsoft.rainbowcake.base.JobViewModel
import javax.inject.Inject

class SharedVMPagerViewModel @Inject constructor(
        private val sharedVMPagerPresenter: SharedVMPagerPresenter
) : JobViewModel<SharedVMPagerViewState>(SharedVMPagerViewState()) {

    fun load() = execute {
        viewState = SharedVMPagerViewState(sharedVMPagerPresenter.getData())
    }

}
