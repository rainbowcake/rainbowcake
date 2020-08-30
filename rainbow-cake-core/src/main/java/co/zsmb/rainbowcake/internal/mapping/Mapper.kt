package co.zsmb.rainbowcake.internal.mapping

class Mapper(val profileProvider: MappingProfileProvider) {

    init {
        profileProvider.registerProfileProvider(this)
    }

    /**
     * Map from TIn type to TOut
     */
    inline fun <reified TIn, reified TOut> map(obj: TIn): TOut {
        val mapping = profileProvider.getMapping<TIn, TOut>()
        return mapping.map(obj)
    }

    /**
     * Map from List<TIn> type to List<TOut>
     */
    inline fun <reified TIn, reified TOut> map(list: Iterable<TIn>): List<TOut> {
        val mapping = profileProvider.getMapping<TIn, TOut>()
        return list.map { mapping.map(it) }
    }

    /**
     * Arrays are not iterable, so we need to do this
     */
    inline fun <reified TIn, reified TOut> map(arr: Array<TIn>): List<TOut> = map(arr.asIterable())
}
