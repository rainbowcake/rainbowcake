package co.zsmb.rainbowcake.internal

import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.internal.livedata.MutableLiveDataCollectionImpl
import co.zsmb.rainbowcake.util.LifecycleTest
import co.zsmb.rainbowcake.util.MockObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


internal class MutableLiveDataCollectionImplTest : LifecycleTest() {

    private val mutableLiveDataCollection: MutableLiveDataCollectionImpl<String> =
            MutableLiveDataCollectionImpl(::MutableLiveData)

    private val observer1 = MockObserver<String>()
    private val observer2 = MockObserver<String>()
    private val observer3 = MockObserver<String>()

    @Before
    fun setUp() {
        lifecycle.handleLifecycleEvent(ON_CREATE)
    }

    @Test
    fun liveDataCreation() {
        mutableLiveDataCollection.observe(this, observer1)
        mutableLiveDataCollection.observe(this, observer2)
        mutableLiveDataCollection.observe(this, observer3)

        assertEquals(3, mutableLiveDataCollection.activeLiveData.size)
    }

    @Test
    fun internalLiveDataCleanupOnDestroy() {
        mutableLiveDataCollection.observe(this, observer1)
        mutableLiveDataCollection.observe(this, observer2)
        mutableLiveDataCollection.observe(this, observer3)

        lifecycle.handleLifecycleEvent(ON_DESTROY)
        assertEquals(0, mutableLiveDataCollection.activeLiveData.size)
    }

    @Test
    fun singleObserver() {
        mutableLiveDataCollection.observe(this, observer1)
        observer1.assertObserved()

        mutableLiveDataCollection.setValue("a")
        lifecycle.handleLifecycleEvent(ON_START)
        observer1.assertObserved("a")
    }

    @Test
    fun multiObserver() {
        mutableLiveDataCollection.observe(this, observer1)
        mutableLiveDataCollection.observe(this, observer2)
        mutableLiveDataCollection.observe(this, observer3)
        observer1.assertObserved()
        observer2.assertObserved()

        mutableLiveDataCollection.setValue("a")
        lifecycle.handleLifecycleEvent(ON_START)
        observer1.assertObserved("a")
        observer2.assertObserved("a")
        observer3.assertObserved("a")
    }

    @Test
    fun multiObserverSetMany() {
        mutableLiveDataCollection.observe(this, observer1)
        mutableLiveDataCollection.observe(this, observer2)
        mutableLiveDataCollection.observe(this, observer3)
        observer1.assertObserved()
        observer2.assertObserved()
        observer3.assertObserved()

        mutableLiveDataCollection.setValue("a")
        mutableLiveDataCollection.setValue("b")
        mutableLiveDataCollection.setValue("c")
        lifecycle.handleLifecycleEvent(ON_START)
        observer1.assertObserved("c")
        observer2.assertObserved("c")
        observer3.assertObserved("c")
    }

}
