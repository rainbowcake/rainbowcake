package co.zsmb.rainbowcake.demo.ui.example

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.demo.ui.bottomsheet.ExampleBottomSheetFragment
import co.zsmb.rainbowcake.demo.ui.dialog.ExampleDialogFragment
import co.zsmb.rainbowcake.demo.ui.foo.FooFragment
import co.zsmb.rainbowcake.demo.ui.koin.KoinFragment
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.SharedVMPagerFragment
import co.zsmb.rainbowcake.demo.ui.viewbinding.ViewBindingSampleFragment
import co.zsmb.rainbowcake.navigation.navigator
import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment : RainbowCakeFragment<ExampleViewState, ExampleViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_example

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedVMDemoButton.setOnClickListener {
            navigator?.add(SharedVMPagerFragment())
        }

        customAnimationDemoButton.setOnClickListener {
            navigator?.add(FooFragment(),
                    enterAnim = R.anim.slide_in_right,
                    exitAnim = R.anim.slide_out_left,
                    popEnterAnim = R.anim.slide_in_left,
                    popExitAnim = R.anim.slide_out_right)
        }

        viewBindingExampleDemoButton.setOnClickListener {
            navigator?.add(ViewBindingSampleFragment())
        }

        koinExampleDemoButton.setOnClickListener {
            navigator?.add(KoinFragment())
        }

        bottomSheetExampleDemoButton.setOnClickListener {
            ExampleBottomSheetFragment().show(requireFragmentManager(), "BottomSheetFragment")
        }

        dialogExampleDemoButton.setOnClickListener {
            ExampleDialogFragment().show(requireFragmentManager(), "DialogFragment")
        }
    }

    override fun render(viewState: ExampleViewState) {

    }

}
