package co.zsmb.rainbowcake.demo.ui

import co.zsmb.rainbowcake.demo.ui.koin.KoinPresenter
import co.zsmb.rainbowcake.demo.ui.koin.KoinViewModel
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages.ScreenPresenter
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages.ScreenViewModel
import org.koin.dsl.module

val UIModule = module {
    factory { KoinPresenter() }
    factory { KoinViewModel(get()) }

    factory { ScreenPresenter() }
    factory { ScreenViewModel(get()) }
}
