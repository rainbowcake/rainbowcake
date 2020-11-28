package co.zsmb.rainbowcake.demo.ui.koin

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.koin.getViewModelFromFactory
import kotlinx.android.synthetic.main.fragment_example.bottomSheetExampleDemoButton
import kotlinx.android.synthetic.main.fragment_koin.*

class KoinFragment : RainbowCakeFragment<KoinViewState, KoinViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_koin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView.setOnClickListener {
            viewModel.load()
        }

        bottomSheetExampleDemoButton.setOnClickListener {
            fragmentManager?.let { KoinBottomSheetFragment().show(it, "FooBottomSheetFragmentKoin") }
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: KoinViewState) {
        textView.text = viewState.data
    }

}
