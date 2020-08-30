package co.zsmb.rainbowcake.internal.mapping

import android.util.Log

typealias ProfileDeclaration = MappingProfile.() -> Unit

/**
 * Represent mapping list for [Mapper].
 * Use [Mapper] for nested mapping.
 * Can use mapping lists from other [MappingProfile]s registered on different modules
 * */
abstract class MappingProfile(private val mappingSetup: ProfileDeclaration) {
    lateinit var mapper: Mapper

    val mappingList = mutableListOf<IMapping<*, *>>()

    /**
     * Declare single mapping [TIn] to [TOut].
     * NOTE: Since [createMap] can use mapping list from other [MappingProfile]s,
     * mapping TypeA to TypeB can be registered only once.
     * */
    inline fun <reified TIn, reified TOut> createMap(crossinline mapCreator: TIn.(Mapper) -> TOut) {
        mappingList.add(
                object : IMapping<TIn, TOut> {
                    override fun map(obj: TIn): TOut {
                        return try {
                            mapCreator.invoke(obj, mapper)
                        } catch (e: Exception) {
                            Log.d(LOG_TAG, "Error mapping: $mappingName")
                            throw e
                        }
                    }

                    override val mappingName: String
                        get() = getMappingName<TIn, TOut>()
                }
        )
    }

    /**
     * Register [MappingProfile] with [mapper].
     * @return mapping list for current [MappingProfile]
     */
    fun register(mapper: Mapper): MutableList<IMapping<*, *>> {
        this.mapper = mapper
        mappingSetup.invoke(this)

        return mappingList
    }

    companion object {
        const val LOG_TAG = "MappingProfile"

        inline fun <reified TIn, reified TOut> getMappingName(): String {
            return TIn::class.qualifiedName + " -> " + TOut::class.qualifiedName
        }
    }
}
