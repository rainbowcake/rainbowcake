package hu.autsoft.rainbowcake.internal

import android.arch.lifecycle.Lifecycle.Event.ON_START
import android.arch.lifecycle.Lifecycle.Event.ON_STOP
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import hu.autsoft.rainbowcake.util.LifecycleTest
import hu.autsoft.rainbowcake.util.MockObserver
import org.junit.Before
import org.junit.Test

@Suppress("UsePropertyAccessSyntax")
class ActiveOnlySingleShotLiveDataTest : LifecycleTest() {

    private val activeOnlyLiveData: MutableLiveData<String> = ActiveOnlySingleShotLiveData()

    private val observer = MockObserver<String>()

    @Before
    fun setUp() {
        activeOnlyLiveData.observe(this, observer)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun postValue() {
        activeOnlyLiveData.postValue("a")
    }

    @Test(expected = IllegalStateException::class)
    fun multipleObservers() {
        activeOnlyLiveData.observe(this, Observer { })
    }

    @Test
    fun inactive() {
        activeOnlyLiveData.setValue("a")
        activeOnlyLiveData.setValue("b")
        activeOnlyLiveData.setValue("c")
        observer.assertObserved()
    }

    @Test
    fun start() {
        activeOnlyLiveData.setValue("a")
        activeOnlyLiveData.setValue("b")
        activeOnlyLiveData.setValue("c")
        observer.assertObserved()

        lifecycle.handleLifecycleEvent(ON_START)
        observer.assertObserved()
    }

    @Test
    fun startStopStart() {
        activeOnlyLiveData.setValue("a")
        observer.assertObserved()

        lifecycle.handleLifecycleEvent(ON_START)
        observer.assertObserved()

        activeOnlyLiveData.setValue("b")
        observer.assertObserved("b")

        lifecycle.handleLifecycleEvent(ON_STOP)
        activeOnlyLiveData.setValue("c")
        observer.assertObserved()
    }

    @Test
    fun observeMany() {
        lifecycle.handleLifecycleEvent(ON_START)
        activeOnlyLiveData.setValue("a")
        activeOnlyLiveData.setValue("b")
        activeOnlyLiveData.setValue("c")
        observer.assertObserved("a", "b", "c")
    }

}
