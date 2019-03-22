package co.zsmb.rainbowcake.demo.ui.sharedvmpager

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class SharedVMPagerPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
