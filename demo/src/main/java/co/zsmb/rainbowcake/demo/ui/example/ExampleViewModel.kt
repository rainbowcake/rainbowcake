package co.zsmb.rainbowcake.demo.ui.example

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
        private val examplePresenter: ExamplePresenter
) : JobViewModel<ExampleViewState>(ExampleViewState)
