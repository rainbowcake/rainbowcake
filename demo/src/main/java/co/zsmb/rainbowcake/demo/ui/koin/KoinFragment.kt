package co.zsmb.rainbowcake.demo.ui.koin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.demo.R
import co.zsmb.rainbowcake.koin.getViewModelFromFactory
import kotlinx.android.synthetic.main.fragment_koin.*
import org.koin.core.parameter.parametersOf

class KoinFragment : RainbowCakeFragment<KoinViewState, KoinViewModel>() {

    override fun getViewResource() = R.layout.fragment_koin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialCount = 10
        val viewModelWithParams = getViewModelFromFactory { parametersOf(initialCount) }
        provideViewModel(viewModelWithParams)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView.setOnClickListener {
            viewModel.incrementCounter()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun render(viewState: KoinViewState) {
        textView.text = "Clicks counter: ${viewState.counter}"
    }

}
