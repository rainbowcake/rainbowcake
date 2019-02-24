package hu.autsoft.rainbowcake.demo.ui.sharedvmpager.pages

import android.os.Bundle
import android.view.View
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.ViewModelScope.ParentFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.demo.R
import kotlinx.android.synthetic.main.fragment_screen_three.*

class ScreenThreeFragment : BaseFragment<ScreenViewState, ScreenViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory(scope = ParentFragment)
    override fun getViewResource() = R.layout.fragment_screen_three
    override val observesEvents = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun render(viewState: ScreenViewState) {
        screenThreeText.text = "Screen Three\n${viewState.data}"
    }

}
