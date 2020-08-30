package co.zsmb.rainbowcake.demo.ui

import co.zsmb.rainbowcake.demo.ui.koin.KoinPresenter
import co.zsmb.rainbowcake.demo.ui.koin.KoinViewModel
import co.zsmb.rainbowcake.demo.ui.mapper.koin.KoinMapperPresenter
import co.zsmb.rainbowcake.demo.ui.mapper.koin.KoinMapperViewModel
import co.zsmb.rainbowcake.demo.ui.mapper.mappingProfiles.MappingExampleProfile
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages.ScreenPresenter
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages.ScreenViewModel
import co.zsmb.rainbowcake.koin.mappingProfile
import co.zsmb.rainbowcake.koin.registerMapper
import org.koin.dsl.module

val UIModule = module {
    factory { KoinPresenter() }
    factory { KoinViewModel(get()) }

    factory { ScreenPresenter() }
    factory { ScreenViewModel(get()) }

    factory { KoinMapperViewModel(get()) }
    factory { KoinMapperPresenter(get()) }

    registerMapper()
    mappingProfile(MappingExampleProfile())
}
