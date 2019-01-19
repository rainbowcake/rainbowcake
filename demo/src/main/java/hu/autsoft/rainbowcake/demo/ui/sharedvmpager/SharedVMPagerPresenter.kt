package hu.autsoft.rainbowcake.demo.ui.sharedvmpager

import hu.autsoft.rainbowcake.withIOContext
import javax.inject.Inject

class SharedVMPagerPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
