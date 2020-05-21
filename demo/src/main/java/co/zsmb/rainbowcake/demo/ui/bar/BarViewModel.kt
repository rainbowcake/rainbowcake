package co.zsmb.rainbowcake.demo.ui.bar

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class BarViewModel @Inject constructor(
        private val barPresenter: BarPresenter
) : RainbowCakeViewModel<BarViewState>(BarViewState()) {

    fun load() = execute {
        viewState = BarViewState(barPresenter.getData())
    }

}
