package co.zsmb.rainbowcake.internal

import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.internal.livedata.QueuedSingleShotLiveData
import co.zsmb.rainbowcake.util.LifecycleTest
import co.zsmb.rainbowcake.util.MockObserver
import org.junit.Before
import org.junit.Test

@Suppress("UsePropertyAccessSyntax")
internal class QueuedSingleShotLiveDataTest : LifecycleTest() {

    private val queuedLiveData: MutableLiveData<String> = QueuedSingleShotLiveData()

    private val observer = MockObserver<String>()

    @Before
    fun setUp() {
        queuedLiveData.observe(this, observer)
    }

    @Test
    fun startStopStart() {
        queuedLiveData.postValue("a")
        observer.assertObserved()

        lifecycle.handleLifecycleEvent(ON_START)
        observer.assertObserved("a")

        queuedLiveData.postValue("b")
        observer.assertObserved("b")

        queuedLiveData.postValue("c")
        lifecycle.handleLifecycleEvent(ON_STOP)
        queuedLiveData.postValue("d")
        queuedLiveData.postValue("e")
        observer.assertObserved("c")

        lifecycle.handleLifecycleEvent(ON_START)
        observer.assertObserved("d", "e")
    }

    @Test
    fun nullValue() {
        lifecycle.handleLifecycleEvent(ON_START)
        queuedLiveData.postValue("a")
        queuedLiveData.postValue(null)
        queuedLiveData.postValue("b")
        observer.assertObserved("a", null, "b")
    }

    @Test(expected = IllegalStateException::class)
    fun multipleObservers() {
        queuedLiveData.observe(this) {}
    }

    @Test
    fun observeRemoveObserve() {
        lifecycle.handleLifecycleEvent(ON_START)
        queuedLiveData.postValue("a")
        observer.assertObserved("a")

        queuedLiveData.removeObserver(observer)
        queuedLiveData.postValue("c")
        queuedLiveData.postValue("d")
        observer.assertObserved()

        queuedLiveData.observe(this, observer)
        observer.assertObserved("c", "d")
    }

}
