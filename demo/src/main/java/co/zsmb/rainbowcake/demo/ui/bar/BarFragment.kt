package co.zsmb.rainbowcake.demo.ui.bar

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R

class BarFragment : RainbowCakeFragment<BarViewState, BarViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_bar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: BarViewState) {
        // TODO Render state
    }

}
