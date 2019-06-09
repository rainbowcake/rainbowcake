package co.zsmb.rainbowcake.demo.ui.koin

import co.zsmb.rainbowcake.base.JobViewModel

class KoinViewModel(
        private val koinPresenter: KoinPresenter
) : JobViewModel<KoinViewState>(KoinViewState()) {

    fun load() = execute {
        viewState = KoinViewState(koinPresenter.getData())
    }

}
