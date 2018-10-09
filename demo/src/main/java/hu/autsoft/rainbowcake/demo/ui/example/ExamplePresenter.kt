package hu.autsoft.rainbowcake.demo.ui.example

import hu.autsoft.rainbowcake.withIOContext
import javax.inject.Inject

class ExamplePresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
