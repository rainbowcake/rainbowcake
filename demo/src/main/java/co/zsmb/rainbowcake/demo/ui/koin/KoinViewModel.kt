package co.zsmb.rainbowcake.demo.ui.koin

import co.zsmb.rainbowcake.base.RainbowCakeViewModel

class KoinViewModel(
        private val koinPresenter: KoinPresenter
) : RainbowCakeViewModel<KoinViewState>(KoinViewState()) {

    fun load() = execute {
        viewState = KoinViewState(koinPresenter.getData())
    }

}
