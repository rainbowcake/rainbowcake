package co.zsmb.rainbowcake.demo.ui.mapper.mappingProfiles

import co.zsmb.rainbowcake.internal.mapping.MappingProfile

class MappingExampleProfile : MappingProfile({
    /**
     * Use mapping declared in [NumbersMappingProfile]
     */
    createMap<Int, String> { mapper ->
        // Mapping Int to Long
        val valueLong = mapper.map<Int, Long>(this)

        // Mapping Long to String
        val longToStringResult: String = mapper.map(valueLong)

        "Mapped Int value $this to Long and after $longToStringResult"
    }
})
