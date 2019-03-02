package hu.autsoft.rainbowcake.internal.livedata

import android.os.Handler
import android.os.Looper
import android.support.annotation.MainThread
import java.util.LinkedList
import java.util.Queue

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
