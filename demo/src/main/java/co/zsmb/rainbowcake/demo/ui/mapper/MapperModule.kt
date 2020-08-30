package co.zsmb.rainbowcake.demo.ui.mapper

import co.zsmb.rainbowcake.demo.ui.mapper.mappingProfiles.NumbersMappingProfile
import co.zsmb.rainbowcake.koin.mappingProfile
import org.koin.dsl.module

val mapperExampleModule = module {
    mappingProfile(NumbersMappingProfile())
}
