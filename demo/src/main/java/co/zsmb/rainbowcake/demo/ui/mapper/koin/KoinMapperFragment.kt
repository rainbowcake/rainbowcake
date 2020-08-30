package co.zsmb.rainbowcake.demo.ui.mapper.koin

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.koin.getViewModelFromFactory
import kotlinx.android.synthetic.main.fragment_mapper.*

class KoinMapperFragment : RainbowCakeFragment<KoinMapperViewState, KoinMapperViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_mapper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        incrementBtn.setOnClickListener {
            viewModel.load()
        }
    }

    override fun render(viewState: KoinMapperViewState) {
        textView.text = viewState.data
    }

}
