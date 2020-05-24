package co.zsmb.rainbowcake.internal.livedata

import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import java.util.LinkedList
import java.util.Queue

/**
 * A [MutableLiveData] implementation that queues all values that are set or posted
 * to it while its observer is inactive. When its observer becomes active,
 * it is notified about any queued values immediately.
 *
 * Subclass of [SingleShotLiveData], therefore it may only have a single
 * observer and delivers the value set in it only - at most - once.
 */
internal class QueuedSingleShotLiveData<T : Any> : SingleShotLiveData<T>() {

    private val queue: Queue<T> = LinkedList<T>()
    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun postValue(value: T?) {
        handler.post { setValue(value) }
    }

    @MainThread
    override fun setValue(t: T?) {
        if (hasActiveObservers() && queue.isEmpty()) {
            super.setValue(t)
        } else {
            queue.add(t)
        }
    }

    override fun onActive() {
        var element = queue.poll()
        while (element != null) {
            super.setValue(element)
            element = queue.poll()
        }
    }

}
