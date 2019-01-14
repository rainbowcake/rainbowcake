package hu.autsoft.rainbowcake.demo.ui.bar

import android.os.Bundle
import android.view.View
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.demo.R

class BarFragment : BaseFragment<BarViewState, BarViewModel>() {

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
