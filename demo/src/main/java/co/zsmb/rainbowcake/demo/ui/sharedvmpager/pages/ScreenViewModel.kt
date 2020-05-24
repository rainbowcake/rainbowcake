package co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import timber.log.Timber
import javax.inject.Inject

class ScreenViewModel @Inject constructor(
        private val screenPresenter: ScreenPresenter
) : RainbowCakeViewModel<ScreenViewState>(ScreenViewState()) {

    init {
        execute {
            viewState = ScreenViewState(screenPresenter.getData())
        }
    }

    override fun onCleared() {
        super.onCleared()

        Timber.d("ScreenViewModel cleared!")
    }

}
