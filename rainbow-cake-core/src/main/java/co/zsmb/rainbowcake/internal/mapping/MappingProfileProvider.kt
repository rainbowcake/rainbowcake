package co.zsmb.rainbowcake.internal.mapping

/**
 * Provide [MappingProfile]s to [Mapper]
 * Also combine [MappingProfile]s by modules, so that can reuse them own mapping.
 **/
class MappingProfileProvider private constructor(private val profiles: MutableList<MappingProfile>) {
    private lateinit var mapper: Mapper
    val mappingProvider = HashMap<String, IMapping<*, *>>()

    constructor() : this(mutableListOf())

    /**
     * Register [MappingProfileProvider] with [mapper] and given [profiles].
     * Note: [profiles] also can be added at future by [addProfile] function.
     */
    fun registerProfileProvider(mapper: Mapper) {
        this.mapper = mapper
        profiles.forEach(::registerProfile)
    }

    /**
     * Provide mapping declared at one of [profiles].
     * @throws IllegalStateException if mapping not declared at any registered [MappingProfile]
     */
    @Suppress("UNCHECKED_CAST") // Suppress because checked by type name
    inline fun <reified TIn, reified TOut> getMapping(): IMapping<TIn, TOut> {
        val mappingName = MappingProfile.getMappingName<TIn, TOut>()

        check(mappingProvider.containsKey(mappingName)) {
            "Mapping for type $mappingName not registered."
        }

        val mapping = mappingProvider[mappingName]

        return mapping as IMapping<TIn, TOut>
    }

    /**
     * Register single [MappingProfile]
     * @throws IllegalStateException if [MappingProfile] has duplicate mapping list
     */
    private fun registerProfile(profile: MappingProfile) {
        val mappingList = profile.register(this.mapper)
        mappingList.forEach { mapping ->
            check(!mappingProvider.containsKey(mapping.mappingName)) {
                "Mapping for type ${mapping.mappingName} already registered."
            }

            mappingProvider[mapping.mappingName] = mapping
        }
    }

    /**
     * Add profile and register profile for [MappingProfileProvider], so [Mapper] can use it after
     * he called [createProfileProvider]
     */
    fun addProfile(profile: MappingProfile) {
        profiles.add(profile)

        // register profile if MappingProfileProvider initialized
        if (::mapper.isInitialized) {
            registerProfile(profile)
        }
    }

    companion object {
        /**
         * Creates [MappingProfileProvider].
         * Note should be registered at [Mapper]
         */
        fun createProfileProvider(profiles: List<MappingProfile>) = MappingProfileProvider(
                profiles.toMutableList()
        )
    }
}
