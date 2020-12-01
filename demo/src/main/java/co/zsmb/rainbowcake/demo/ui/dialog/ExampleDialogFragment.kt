package co.zsmb.rainbowcake.demo.ui.dialog

import co.zsmb.rainbowcake.base.RainbowCakeDialogFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.demo.ui.example.ExampleViewModel
import co.zsmb.rainbowcake.demo.ui.example.ExampleViewState
import kotlinx.android.synthetic.main.fragment_example_dialog.*

class ExampleDialogFragment : RainbowCakeDialogFragment<ExampleViewState, ExampleViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_example_dialog

    override fun render(viewState: ExampleViewState) {
        exampleDialogFragmentText.text
        viewModel
    }

}
