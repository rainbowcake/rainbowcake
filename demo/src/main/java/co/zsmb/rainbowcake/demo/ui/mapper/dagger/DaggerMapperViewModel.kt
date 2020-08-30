package co.zsmb.rainbowcake.demo.ui.mapper.dagger

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class DaggerMapperViewModel @Inject constructor(
    private val mapperPresenter: DaggerMapperPresenter
) : RainbowCakeViewModel<DaggerMapperViewState>(DaggerMapperViewState("Dagger Mapper Screen")) {

    fun load() = execute {
        viewState = DaggerMapperViewState(mapperPresenter.getData())
    }
}
