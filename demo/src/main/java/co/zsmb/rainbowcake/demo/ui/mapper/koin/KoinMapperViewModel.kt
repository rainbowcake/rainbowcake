package co.zsmb.rainbowcake.demo.ui.mapper.koin

import co.zsmb.rainbowcake.base.RainbowCakeViewModel

class KoinMapperViewModel(
    private val mapperPresenter: KoinMapperPresenter
) : RainbowCakeViewModel<KoinMapperViewState>(KoinMapperViewState("Koin Mapper Screen")) {

    fun load() = execute {
        viewState = KoinMapperViewState(mapperPresenter.getData())
    }
}
