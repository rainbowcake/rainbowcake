package co.zsmb.rainbowcake.demo.ui.foo

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.demo.ui.bar.BarFragment
import co.zsmb.rainbowcake.navigation.navigator
import kotlinx.android.synthetic.main.fragment_foo.*

class FooFragment : RainbowCakeFragment<FooViewState, FooViewModel>() {

    override fun getViewResource() = R.layout.fragment_foo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provideViewModel(getViewModelFromFactory())
    }

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
