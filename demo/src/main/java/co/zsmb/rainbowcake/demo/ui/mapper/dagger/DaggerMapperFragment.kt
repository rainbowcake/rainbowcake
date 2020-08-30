package co.zsmb.rainbowcake.demo.ui.mapper.dagger

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import kotlinx.android.synthetic.main.fragment_mapper.*

class DaggerMapperFragment : RainbowCakeFragment<DaggerMapperViewState, DaggerMapperViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_mapper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        incrementBtn.setOnClickListener {
            viewModel.load()
        }
    }

    override fun render(viewState: DaggerMapperViewState) {
        textView.text = viewState.data
    }
}
