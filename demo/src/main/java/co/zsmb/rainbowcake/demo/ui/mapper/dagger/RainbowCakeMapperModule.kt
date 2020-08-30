package co.zsmb.rainbowcake.demo.ui.mapper.dagger

import co.zsmb.rainbowcake.demo.ui.mapper.mappingProfiles.MappingExampleProfile
import co.zsmb.rainbowcake.demo.ui.mapper.mappingProfiles.NumbersMappingProfile
import co.zsmb.rainbowcake.internal.mapping.Mapper
import co.zsmb.rainbowcake.internal.mapping.MappingProfile
import co.zsmb.rainbowcake.internal.mapping.MappingProfileProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
class RainbowCakeMapperModule {
    @Provides
    @Singleton
    fun provideMappingProfileProvider(
            profiles: Set<@JvmSuppressWildcards MappingProfile>
    ): MappingProfileProvider {
        return MappingProfileProvider
                .createProfileProvider(profiles.toList())
    }

    @Provides
    @Singleton
    fun provideMapper(mappingProfileProvider: MappingProfileProvider): Mapper {
        return Mapper(mappingProfileProvider)
    }

    @Provides
    @IntoSet
    fun provideNumbersMappingProfile(): MappingProfile = NumbersMappingProfile()

    @Provides
    @IntoSet
    fun provideMappingExampleProfile(): MappingProfile = MappingExampleProfile()
}