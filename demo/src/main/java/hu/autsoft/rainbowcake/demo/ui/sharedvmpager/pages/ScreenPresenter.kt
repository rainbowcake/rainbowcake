package hu.autsoft.rainbowcake.demo.ui.sharedvmpager.pages

import hu.autsoft.rainbowcake.withIOContext
import javax.inject.Inject
import kotlin.random.Random

class ScreenPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        Random.nextDouble().toString()
    }

}
