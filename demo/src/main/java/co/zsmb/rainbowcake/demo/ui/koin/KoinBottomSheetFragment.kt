package co.zsmb.rainbowcake.demo.ui.koin

import co.zsmb.rainbowcake.base.RainbowCakeBottomFragment
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.koin.getViewModelFromFactory
import kotlinx.android.synthetic.main.fragment_bottom_sheet_foo.fooBottomSheetFragmentText

class KoinBottomSheetFragment : RainbowCakeBottomFragment<KoinViewState, KoinViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_bottom_sheet_foo
    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: KoinViewState) {
        fooBottomSheetFragmentText.text = viewState.data
    }

}
