package co.zsmb.rainbowcake.demo.ui.example

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

//import javax.inject.Inject

class ExamplePresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
