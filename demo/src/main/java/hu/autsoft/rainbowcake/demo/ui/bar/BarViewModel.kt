package hu.autsoft.rainbowcake.demo.ui.bar

import hu.autsoft.rainbowcake.base.JobViewModel
import javax.inject.Inject

class BarViewModel @Inject constructor(
        private val barPresenter: BarPresenter
) : JobViewModel<BarViewState>(BarViewState()) {

    fun load() = execute {
        viewState = BarViewState(barPresenter.getData())
    }

}
