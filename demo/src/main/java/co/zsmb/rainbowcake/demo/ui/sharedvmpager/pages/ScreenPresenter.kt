package co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject
import kotlin.random.Random

class ScreenPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        Random.nextDouble().toString()
    }

}
