package co.zsmb.rainbowcake.demo.ui.koin

import co.zsmb.rainbowcake.withIOContext

class KoinPresenter {

    // Very bad stateful presenter, don't do this
    var counter = 0

    suspend fun incrementCounter(): Int = withIOContext {
        counter++
    }

}
