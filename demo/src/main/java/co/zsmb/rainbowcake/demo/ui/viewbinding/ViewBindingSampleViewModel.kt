package co.zsmb.rainbowcake.demo.ui.viewbinding

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class ViewBindingSampleViewModel @Inject constructor()
    : RainbowCakeViewModel<ViewBindingSampleState>(initialState = ViewBindingSampleState("")) {
    fun load() = execute {
        viewState = ViewBindingSampleState("View Binding sample loaded!")
    }
}
