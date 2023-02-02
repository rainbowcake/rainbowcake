package co.zsmb.rainbowcake.internal.config

import co.zsmb.rainbowcake.config.isNotLoggable
import junit.framework.Assert.assertEquals
import org.junit.Test

internal class RainbowCakeConfigurationTest {

    @Test
    fun configureIsLoggableWithDebugTrueLoggableTrue() {
        RainbowCakeConfiguration.apply {
            isDebug = true
            isLoggable = true
        }

        assertEquals(false, RainbowCakeConfiguration.isNotLoggable)
    }

    @Test
    fun configureIsLoggableWithDebugTrueLoggableFalse() {
        RainbowCakeConfiguration.apply {
            isDebug = true
            isLoggable = false
        }

        assertEquals(true, RainbowCakeConfiguration.isNotLoggable)
    }

    @Test
    fun configureIsLoggableWithDebugFalseLoggableTrue() {
        RainbowCakeConfiguration.apply {
            isDebug = false
            isLoggable = true
        }

        assertEquals(true, RainbowCakeConfiguration.isNotLoggable)
    }

    @Test
    fun configureIsLoggableWithDebugFalseLoggableFalse() {
        RainbowCakeConfiguration.apply {
            isDebug = false
            isLoggable = false
        }

        assertEquals(true, RainbowCakeConfiguration.isNotLoggable)
    }

    @Test
    fun configureIsLoggableWithDebugFalseForRetroCompatibility() {
        RainbowCakeConfiguration.apply {
            isDebug = false
        }

        assertEquals(true, RainbowCakeConfiguration.isNotLoggable)
    }

    @Test
    fun configureIsLoggableWithDebugTrueForRetroCompatibility() {
        RainbowCakeConfiguration.apply {
            isDebug = true
        }

        assertEquals(true, RainbowCakeConfiguration.isNotLoggable)
    }
}
