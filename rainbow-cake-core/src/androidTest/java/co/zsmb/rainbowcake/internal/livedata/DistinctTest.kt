@file:Suppress("UsePropertyAccessSyntax")

package co.zsmb.rainbowcake.internal.livedata

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import co.zsmb.rainbowcake.util.LifecycleTest
import co.zsmb.rainbowcake.util.MockObserver
import org.junit.Before
import org.junit.Test

class DistinctTest : LifecycleTest() {

    private val mutableLiveData: MutableLiveData<String> = MutableLiveData()
    private val liveData: LiveData<String> = mutableLiveData.distinct()

    private val observer1 = MockObserver<String>()
    private val observer2 = MockObserver<String>()

    @Before
    fun setup() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    @Test
    fun oneObserver() {
        liveData.observe(this, observer1)

        mutableLiveData.setValue("a")
        mutableLiveData.setValue("b")
        mutableLiveData.setValue("b")
        mutableLiveData.setValue("c")
        mutableLiveData.setValue("c")
        mutableLiveData.setValue("c")

        observer1.assertObserved("a", "b", "c")
    }

    @Test
    fun twoObservers() {
        liveData.observe(this, observer1)

        mutableLiveData.setValue("a")
        mutableLiveData.setValue("b")
        mutableLiveData.setValue("b")
        mutableLiveData.setValue("c")
        mutableLiveData.setValue("c")
        mutableLiveData.setValue("c")

        observer1.assertObserved("a", "b", "c")

        liveData.removeObserver(observer1)

        liveData.observe(this, observer2)

        observer2.assertObserved("c")
    }

}
