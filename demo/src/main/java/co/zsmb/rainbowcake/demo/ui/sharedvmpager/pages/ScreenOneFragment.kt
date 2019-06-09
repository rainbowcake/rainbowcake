package co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.ViewModelScope.ParentFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import kotlinx.android.synthetic.main.fragment_screen_one.*

class ScreenOneFragment : RainbowCakeFragment<ScreenViewState, ScreenViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory(scope = ParentFragment)
    override fun getViewResource() = R.layout.fragment_screen_one

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun render(viewState: ScreenViewState) {
        screenOneText.text = "Screen One\n${viewState.data}"
    }

}
