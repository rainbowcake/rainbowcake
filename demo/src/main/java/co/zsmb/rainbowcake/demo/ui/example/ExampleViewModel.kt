package co.zsmb.rainbowcake.demo.ui.example

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
        private val examplePresenter: ExamplePresenter
) : RainbowCakeViewModel<ExampleViewState>(ExampleViewState)
