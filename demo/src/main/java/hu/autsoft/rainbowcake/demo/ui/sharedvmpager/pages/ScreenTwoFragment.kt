package hu.autsoft.rainbowcake.demo.ui.sharedvmpager.pages

import android.os.Bundle
import android.view.View
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.demo.R
import kotlinx.android.synthetic.main.fragment_screen_two.*

class ScreenTwoFragment : BaseFragment<ScreenViewState, ScreenViewModel>() {

    private val vmKey: String
        get() = parentFragment!!.hashCode().toString()

    override fun provideViewModel() = getViewModelFromFactory(vmKey)
    override fun getViewResource() = R.layout.fragment_screen_two

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun render(viewState: ScreenViewState) {
        screenTwoText.text = "Screen Two\n${viewState.data}"
    }

}
