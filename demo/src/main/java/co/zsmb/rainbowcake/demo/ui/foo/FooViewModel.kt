package co.zsmb.rainbowcake.demo.ui.foo

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class FooViewModel @Inject constructor(
        private val fooPresenter: FooPresenter
) : RainbowCakeViewModel<FooViewState>(FooViewState()) {

    fun load() = execute {
        viewState = FooViewState(fooPresenter.getData())
    }

}
