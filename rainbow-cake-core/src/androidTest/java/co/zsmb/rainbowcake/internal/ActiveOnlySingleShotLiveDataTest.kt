package co.zsmb.rainbowcake.internal

import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import co.zsmb.rainbowcake.internal.livedata.ActiveOnlySingleShotLiveData
import co.zsmb.rainbowcake.util.LifecycleTest
import co.zsmb.rainbowcake.util.MockObserver
import org.junit.Before
import org.junit.Test

class ActiveOnlySingleShotLiveDataTest : LifecycleTest() {

    private val activeOnlyLiveData: MutableLiveData<String> = ActiveOnlySingleShotLiveData()

    private val observer = MockObserver<String>()

    @Before
    fun setUp() {
        activeOnlyLiveData.observe(this, observer)
    }

    @Test(expected = IllegalStateException::class)
    fun multipleObservers() {
        activeOnlyLiveData.observe(this, Observer { })
    }

    @Test
    fun inactive() {
        activeOnlyLiveData.postValue("a")
        activeOnlyLiveData.postValue("b")
        activeOnlyLiveData.postValue("c")
        observer.assertObserved()
    }

    @Test
    fun start() {
        activeOnlyLiveData.postValue("a")
        activeOnlyLiveData.postValue("b")
        activeOnlyLiveData.postValue("c")
        observer.assertObserved()

        lifecycle.handleLifecycleEvent(ON_START)
        observer.assertObserved()
    }

    @Test
    fun startStopStart() {
        activeOnlyLiveData.postValue("a")
        observer.assertObserved()

        lifecycle.handleLifecycleEvent(ON_START)
        observer.assertObserved()

        activeOnlyLiveData.postValue("b")
        observer.assertObserved("b")

        lifecycle.handleLifecycleEvent(ON_STOP)
        activeOnlyLiveData.postValue("c")
        observer.assertObserved()
    }

    @Test
    fun observeMany() {
        lifecycle.handleLifecycleEvent(ON_START)
        activeOnlyLiveData.postValue("a")
        activeOnlyLiveData.postValue("b")
        activeOnlyLiveData.postValue("c")
        observer.assertObserved("a", "b", "c")
    }

}
