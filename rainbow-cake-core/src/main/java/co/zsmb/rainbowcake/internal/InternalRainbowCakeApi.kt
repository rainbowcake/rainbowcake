package co.zsmb.rainbowcake.internal

@RequiresOptIn(
        level = RequiresOptIn.Level.ERROR,
        message = "This is internal API for RainbowCake, meant to be used only by the modules of RainbowCake itself. " +
                "There are no API stability guarantees for this API, you shouldn't build on it as a client.",
)
public annotation class InternalRainbowCakeApi
