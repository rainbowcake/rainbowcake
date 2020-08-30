package co.zsmb.rainbowcake.demo.ui.mapper.mappingProfiles

import co.zsmb.rainbowcake.internal.mapping.MappingProfile

class NumbersMappingProfile : MappingProfile({
    // Declare Int to Long mapper
    createMap<Int, Long> { this.toLong() }

    // Declare Long to String mapper
    createMap<Long, String> {
        "Mapped Long value $this to String"
    }
})