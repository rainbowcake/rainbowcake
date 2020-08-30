package co.zsmb.rainbowcake.koin

import co.zsmb.rainbowcake.internal.mapping.Mapper
import co.zsmb.rainbowcake.internal.mapping.MappingProfile
import co.zsmb.rainbowcake.internal.mapping.MappingProfileProvider
import org.koin.core.module.Module
import org.koin.core.qualifier.named

/**
 * Extension method for module for profile registration in koin
 */
inline fun <reified T : MappingProfile> Module.mappingProfile(profile: T) = single<MappingProfile>(
        named<T>()
) {
    val profileProvider = get<MappingProfileProvider>()
    profileProvider.addProfile(profile)
    profile
}

fun Module.registerMapper() {
    single { MappingProfileProvider() }
    single {
        getAll<MappingProfile>() // Force all declared mappingProfiles to init
        Mapper(get())
    }
}
