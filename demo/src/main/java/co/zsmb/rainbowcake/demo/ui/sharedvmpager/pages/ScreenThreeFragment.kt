package co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.ViewModelScope.ParentFragment
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.koin.getViewModelFromFactory
import kotlinx.android.synthetic.main.fragment_screen_three.*

class ScreenThreeFragment : RainbowCakeFragment<ScreenViewState, ScreenViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory(scope = ParentFragment)
    override fun getViewResource() = R.layout.fragment_screen_three

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun render(viewState: ScreenViewState) {
        screenThreeText.text = "Screen Three\n${viewState.data}"
    }

}
