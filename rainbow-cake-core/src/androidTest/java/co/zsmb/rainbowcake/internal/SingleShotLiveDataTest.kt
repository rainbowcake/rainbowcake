package co.zsmb.rainbowcake.internal

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import co.zsmb.rainbowcake.internal.livedata.SingleShotLiveData
import co.zsmb.rainbowcake.util.LifecycleTest
import co.zsmb.rainbowcake.util.MockObserver
import org.junit.Assert.assertTrue
import org.junit.Test

@Suppress("UsePropertyAccessSyntax")
class SingleShotLiveDataTest : LifecycleTest() {

    private val singleShotLiveData: MutableLiveData<String> = SingleShotLiveData()

    private val mockObserver = MockObserver<String>()

    @Test(expected = IllegalStateException::class)
    fun multipleObservers() {
        singleShotLiveData.observe(this, mockObserver)
        singleShotLiveData.observe(this, Observer {})
    }

    @Test
    fun dispatchedJustOnce() {
        singleShotLiveData.observe(this, mockObserver)

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

        singleShotLiveData.setValue("a")
        singleShotLiveData.setValue("b")

        mockObserver.assertObserved("a", "b")

        singleShotLiveData.removeObserver(mockObserver)
        singleShotLiveData.observe(this, mockObserver)

        mockObserver.assertObservedNothing()
    }

    @Test
    fun removeObserverManually() {
        singleShotLiveData.observe(this, mockObserver)
        singleShotLiveData.removeObserver(mockObserver)

        assertTrue(!singleShotLiveData.hasObservers())
    }

    @Test
    fun removeObserverViaLifecycle() {
        singleShotLiveData.observe(this, mockObserver)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

        assertTrue(singleShotLiveData.hasObservers())

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)

        assertTrue(!singleShotLiveData.hasObservers())
    }

    private fun MockObserver<*>.assertObservedNothing() {
        assertObserved()
    }

}
