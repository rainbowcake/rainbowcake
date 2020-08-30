package co.zsmb.rainbowcake.internal.mapping

interface IMapping<TIn, TOut> {
    fun map(obj: TIn): TOut

    val mappingName: String
}
