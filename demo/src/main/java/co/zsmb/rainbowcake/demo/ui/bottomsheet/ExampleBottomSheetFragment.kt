package co.zsmb.rainbowcake.demo.ui.bottomsheet

import co.zsmb.rainbowcake.base.RainbowCakeBottomFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.demo.ui.foo.FooViewModel
import co.zsmb.rainbowcake.demo.ui.foo.FooViewState
import kotlinx.android.synthetic.main.fragment_example_bottom_sheet.*

class ExampleBottomSheetFragment : RainbowCakeBottomFragment<FooViewState, FooViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_example_bottom_sheet
    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: FooViewState) {
        exampleBottomSheetFragmentText.text = viewState.data
    }

}
