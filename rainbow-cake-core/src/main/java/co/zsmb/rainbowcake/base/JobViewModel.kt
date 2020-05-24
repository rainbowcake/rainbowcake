package co.zsmb.rainbowcake.base

@Deprecated(
        "Use RainbowCakeViewModel instead, as coroutine support is now provided there",
        replaceWith = ReplaceWith("co.zsmb.rainbowcake.base.RainbowCakeViewModel<VS>"),
        level = DeprecationLevel.WARNING
)
abstract class JobViewModel<VS : Any>(initialState: VS) : RainbowCakeViewModel<VS>(initialState)
