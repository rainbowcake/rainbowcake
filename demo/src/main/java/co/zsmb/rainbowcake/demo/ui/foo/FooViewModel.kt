package co.zsmb.rainbowcake.demo.ui.foo

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class FooViewModel @Inject constructor(
        private val fooPresenter: FooPresenter
) : JobViewModel<FooViewState>(FooViewState()) {

    fun load() = execute {
        viewState = FooViewState(fooPresenter.getData())
    }

}
