package hu.autsoft.rainbowcake.demo.ui.example

import android.os.Bundle
import android.view.View
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.demo.R
import hu.autsoft.rainbowcake.extensions.exhaustive

class ExampleFragment : BaseFragment<ExampleViewState, ExampleViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_example

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: ExampleViewState) {
        when (viewState) {
            is DataState -> {
                // TODO render
            }
            NoDataState -> {
                // TODO render
            }
        }.exhaustive
    }

}
