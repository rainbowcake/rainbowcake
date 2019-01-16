package hu.autsoft.rainbowcake.demo.ui.sharedvmpager.pages

import hu.autsoft.rainbowcake.base.JobViewModel
import javax.inject.Inject

class ScreenViewModel @Inject constructor(
        private val screenPresenter: ScreenPresenter
) : JobViewModel<ScreenViewState>(ScreenViewState()) {

    init {
        execute {
            viewState = ScreenViewState(screenPresenter.getData())
        }
    }

}
