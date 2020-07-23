package co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.ViewModelScope
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import kotlinx.android.synthetic.main.fragment_screen_two.*

class ScreenTwoFragment : RainbowCakeFragment<ScreenViewState, ScreenViewModel>() {

    override fun getViewResource() = R.layout.fragment_screen_two

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provideViewModel(getViewModelFromFactory(scope = ViewModelScope.Activity))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun render(viewState: ScreenViewState) {
        screenTwoText.text = "Screen Two\n${viewState.data}"
    }

}
