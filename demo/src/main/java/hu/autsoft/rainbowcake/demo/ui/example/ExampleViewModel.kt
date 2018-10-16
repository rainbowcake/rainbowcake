package hu.autsoft.rainbowcake.demo.ui.example

import hu.autsoft.rainbowcake.base.JobViewModel
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
        private val examplePresenter: ExamplePresenter
) : JobViewModel<ExampleViewState>(NoDataState) {

    fun load() = execute {
        viewState = DataState(examplePresenter.getData())
    }

}
