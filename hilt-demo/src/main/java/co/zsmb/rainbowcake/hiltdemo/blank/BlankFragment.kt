package co.zsmb.rainbowcake.hiltdemo.blank

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.hilt.getViewModel
import co.zsmb.rainbowcake.hiltdemo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlankFragment : RainbowCakeFragment<BlankViewState, BlankViewModel>() {

    override fun provideViewModel() = getViewModel()
    override fun getViewResource() = R.layout.fragment_blank

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.load()
    }

    override fun render(viewState: BlankViewState) = Unit
}