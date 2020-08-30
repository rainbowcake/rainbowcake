package co.zsmb.rainbowcake.demo.ui.mapper.koin

import co.zsmb.rainbowcake.internal.mapping.Mapper
import co.zsmb.rainbowcake.withIOContext

class KoinMapperPresenter(private val mapper: Mapper) {

    // Very bad stateful presenter, don't do this
    var calls = 0

    suspend fun getData(): String = withIOContext {
        mapper.map<Int, String>(calls++)
    }

}
