package co.zsmb.rainbowcake.demo.ui.foo

import co.zsmb.rainbowcake.base.RainbowCakeBottomFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import kotlinx.android.synthetic.main.fragment_bottom_sheet_foo.fooBottomSheetFragmentText

class FooBottomSheetFragment : RainbowCakeBottomFragment<FooViewState, FooViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_bottom_sheet_foo
    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: FooViewState) {
        fooBottomSheetFragmentText.text = viewState.data
    }

}
