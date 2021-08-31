package co.zsmb.rainbowcake.hiltdemo.blank

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlankViewModel @Inject constructor(
    private val presenter: BlankPresenter,
) : RainbowCakeViewModel<BlankViewState>(Initial) {

    fun load() = execute {
        viewState = BlankReady(presenter.getData())
    }
}