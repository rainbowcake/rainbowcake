package hu.autsoft.rainbowcake.demo.ui.foo

import android.os.Bundle
import android.view.View
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.demo.R
import hu.autsoft.rainbowcake.demo.ui.bar.BarFragment
import hu.autsoft.rainbowcake.navigation.navigator
import kotlinx.android.synthetic.main.fragment_foo.*

class FooFragment : BaseFragment<FooViewState, FooViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_foo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fooFragmentRoot.setOnClickListener {
            navigator?.replace(BarFragment(),
                    enterAnim = R.anim.slide_in_right,
                    exitAnim = R.anim.slide_out_left,
                    popEnterAnim = R.anim.slide_in_left,
                    popExitAnim = R.anim.slide_out_right)
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: FooViewState) {
        // TODO Render state
    }

}
