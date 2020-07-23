package co.zsmb.rainbowcake.demo.ui.koin

import co.zsmb.rainbowcake.base.RainbowCakeViewModel

class KoinViewModel(
        initialCount: Int,
        private val koinPresenter: KoinPresenter
) : RainbowCakeViewModel<KoinViewState>(KoinViewState()) {

    init {
        koinPresenter.counter = initialCount
    }

    fun incrementCounter() = execute {
        koinPresenter.incrementCounter()
        viewState = KoinViewState(koinPresenter.counter)
    }

}
