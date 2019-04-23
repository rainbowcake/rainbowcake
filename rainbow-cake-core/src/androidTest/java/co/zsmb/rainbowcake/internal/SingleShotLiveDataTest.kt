package co.zsmb.rainbowcake.internal

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.internal.livedata.SingleShotLiveData
import co.zsmb.rainbowcake.util.LifecycleTest
import co.zsmb.rainbowcake.util.MockObserver
import org.junit.Before
import org.junit.Test

@Suppress("UsePropertyAccessSyntax")
class SingleShotLiveDataTest : LifecycleTest() {

    private val singleShotLiveData: MutableLiveData<String> = SingleShotLiveData()

    private val mockObserver = MockObserver<String>()

    @Before
    fun setUp() {
        singleShotLiveData.observe(this, mockObserver)
    }

    @Test(expected = IllegalStateException::class)
    fun multipleObservers() {
        singleShotLiveData.observe(this, MockObserver<String>())
    }

    @Test
    fun dispatchedJustOnce() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

        singleShotLiveData.setValue("a")
        singleShotLiveData.setValue("b")

        mockObserver.assertObserved("a", "b")

        singleShotLiveData.removeObserver(mockObserver)
        singleShotLiveData.observe(this, mockObserver)

        mockObserver.assertObservedNothing()
    }

    private fun MockObserver<*>.assertObservedNothing() {
        assertObserved()
    }

}
