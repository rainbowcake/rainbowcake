package hu.autsoft.rainbowcake.demo.ui.foo

import hu.autsoft.rainbowcake.withIOContext
import javax.inject.Inject

class FooPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
