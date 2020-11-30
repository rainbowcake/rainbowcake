package co.zsmb.rainbowcake.demo.ui.bottomsheet

import co.zsmb.rainbowcake.base.RainbowCakeBottomSheetFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.demo.ui.example.ExampleViewModel
import co.zsmb.rainbowcake.demo.ui.example.ExampleViewState
import kotlinx.android.synthetic.main.fragment_example_bottom_sheet.*

class ExampleBottomSheetFragment : RainbowCakeBottomSheetFragment<ExampleViewState, ExampleViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_example_bottom_sheet

    override fun render(viewState: ExampleViewState) {
        exampleBottomSheetFragmentText.text
        viewModel
    }

}
