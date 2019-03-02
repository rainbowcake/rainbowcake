package hu.autsoft.rainbowcake.internal

import android.arch.lifecycle.Lifecycle.Event.ON_START
import android.arch.lifecycle.Lifecycle.Event.ON_STOP
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import hu.autsoft.rainbowcake.internal.livedata.QueuedSingleShotLiveData
import hu.autsoft.rainbowcake.util.LifecycleTest
import hu.autsoft.rainbowcake.util.MockObserver
import org.junit.Before
import org.junit.Test

@Suppress("UsePropertyAccessSyntax")
class QueuedSingleShotLiveDataTest : LifecycleTest() {

    private val queuedLiveData: MutableLiveData<String> = QueuedSingleShotLiveData()

    private val observer = MockObserver<String>()

    @Before
    fun setUp() {
        queuedLiveData.observe(this, observer)
    }

    @Test
    fun startStopStart() {
        queuedLiveData.setValue("a")
        observer.assertObserved()

        lifecycle.handleLifecycleEvent(ON_START)
        observer.assertObserved("a")

        queuedLiveData.setValue("b")
        observer.assertObserved("b")

        queuedLiveData.setValue("c")
        lifecycle.handleLifecycleEvent(ON_STOP)
        queuedLiveData.setValue("d")
        queuedLiveData.setValue("e")
        observer.assertObserved("c")

        lifecycle.handleLifecycleEvent(ON_START)
        observer.assertObserved("d", "e")
    }

    @Test
    fun nullValue() {
        lifecycle.handleLifecycleEvent(ON_START)
        queuedLiveData.setValue("a")
        queuedLiveData.setValue(null)
        queuedLiveData.setValue("b")
        observer.assertObserved("a", null, "b")
    }

    @Test(expected = IllegalStateException::class)
    fun multipleObservers() {
        queuedLiveData.observe(this, Observer { })
    }

    @Test
    fun observeRemoveObserve() {
        lifecycle.handleLifecycleEvent(ON_START)
        queuedLiveData.setValue("a")
        observer.assertObserved("a")

        queuedLiveData.removeObserver(observer)
        queuedLiveData.setValue("c")
        queuedLiveData.setValue("d")
        observer.assertObserved()

        queuedLiveData.observe(this, observer)
        observer.assertObserved("c", "d")
    }

}
