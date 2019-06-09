package co.zsmb.rainbowcake.demo.ui.koin

import co.zsmb.rainbowcake.withIOContext

class KoinPresenter {

    // Very bad stateful presenter, don't do this
    var calls = 0

    suspend fun getData(): String = withIOContext {
        "Koin screen loaded ${calls++}"
    }

}
