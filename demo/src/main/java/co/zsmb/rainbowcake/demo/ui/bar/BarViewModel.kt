package co.zsmb.rainbowcake.demo.ui.bar

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class BarViewModel @Inject constructor(
        private val barPresenter: BarPresenter
) : JobViewModel<BarViewState>(BarViewState()) {

    fun load() = execute {
        viewState = BarViewState(barPresenter.getData())
    }

}
