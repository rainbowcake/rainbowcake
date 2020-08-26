package co.zsmb.rainbowcake.internal

import co.zsmb.rainbowcake.internal.livedata.ClairvoyantLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import org.junit.Assert.assertEquals
import org.junit.Test

@Suppress("UsePropertyAccessSyntax")
internal class ClairvoyantLiveDataTest {

    @Test
    fun placeValueIsInstant() = runBlocking(Dispatchers.Main) {
        val liveData = ClairvoyantLiveData<Int>()

        liveData.setValue(1)
        liveData.placeValue(2)

        assertEquals(2, liveData.value)
    }

    @Test
    fun postValueIsNotInstant() = runBlocking(Dispatchers.Main) {
        val liveData = ClairvoyantLiveData<Int>()

        liveData.setValue(1)
        liveData.postValue(2)

        assertEquals(1, liveData.value)
    }

    @Test
    fun placeValueWorksWithMultipleCalls() = runBlocking(Dispatchers.Main) {
        val liveData = ClairvoyantLiveData<Int>()

        liveData.setValue(1)
        liveData.placeValue(2)
        liveData.placeValue(3)
        liveData.placeValue(2)
        liveData.placeValue(4)

        assertEquals(4, liveData.value)
    }

    @Test
    fun placeValueIsOverwrittenBySetValue() = runBlocking(Dispatchers.Main) {
        val liveData = ClairvoyantLiveData<Int>()

        liveData.placeValue(1)
        liveData.setValue(2)
        assertEquals(2, liveData.value)
    }

    @Test
    fun placeValueWithSetValue() = runBlocking(Dispatchers.Main) {
        val liveData = ClairvoyantLiveData<Int>()

        liveData.placeValue(1) // immediately takes effect for getValue calls
        assertEquals(1, liveData.value)

        liveData.setValue(2) // naturally, takes effect for getValue calls
        assertEquals(2, liveData.value)

        yield() // placeValue will complete, actually setting the parent LiveData!
        assertEquals(1, liveData.value)
    }

    @Test
    fun placeValueWithPostValue() = runBlocking(Dispatchers.Main) {
        val liveData = ClairvoyantLiveData<Int>()

        liveData.placeValue(1) // immediately takes effect for getValue calls
        liveData.postValue(2)
        assertEquals(1, liveData.value)

        yield() // both async sets complete, postValue was the latter
        assertEquals(2, liveData.value)

        liveData.postValue(3)
        liveData.placeValue(4) // immediately takes effect for getValue calls
        assertEquals(4, liveData.value)

        yield() // both async sets complete, placeValue was the latter
        assertEquals(4, liveData.value)
    }

}
