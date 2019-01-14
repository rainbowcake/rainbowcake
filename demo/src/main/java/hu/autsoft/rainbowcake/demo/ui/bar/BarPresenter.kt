package hu.autsoft.rainbowcake.demo.ui.bar

import hu.autsoft.rainbowcake.withIOContext
import javax.inject.Inject

class BarPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
