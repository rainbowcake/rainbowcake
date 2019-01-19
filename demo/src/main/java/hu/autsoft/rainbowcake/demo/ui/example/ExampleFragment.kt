package hu.autsoft.rainbowcake.demo.ui.example

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.demo.R
import hu.autsoft.rainbowcake.demo.ui.foo.FooFragment
import hu.autsoft.rainbowcake.extensions.exhaustive
import hu.autsoft.rainbowcake.navigation.navigator
import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment : BaseFragment<ExampleViewState, ExampleViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_example

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exampleFragmentRoot.setOnClickListener {
            navigator?.add(FooFragment(),
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
