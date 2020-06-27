@file:SuppressLint("VisibleForTests")

package co.zsmb.rainbowcake.test

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.QueuedOneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeViewModel

/**
 * Runs the given lambda with two [MockObserver] instances attached
 * to the receiver [RainbowCakeViewModel] instance.
 *
 * The `stateObserver` and `eventsObserver` may be used to assert
 * that the ViewModel's state changed to a given value, or that
 * it has emitted certain events.
 *
 * Usage example:
 *
 * ```
 * vm.observeStateAndEvents { stateObserver, eventsObserver ->
 *     vm.loadArticle(1L)
 *     stateObserver.assertObserved(Loading, ArticleLoaded())
 *
 *     vm.loadArticle(-1L)
 *     eventsObserver.assertObserved(InvalidIdError)
 * }
 * ```
 */
fun <VS : Any> RainbowCakeViewModel<VS>.observeStateAndEvents(
        observers: (stateObserver: MockObserver<VS>, eventsObserver: MockObserver<OneShotEvent>) -> Unit
) {
    val stateObserver = MockLiveDataObserver<VS>()
    val eventsObserver = MockLiveDataObserver<OneShotEvent>()

    val lifecycleOwner = object : LifecycleOwner {
        val lifecycle = LifecycleRegistry(this)
        override fun getLifecycle(): Lifecycle {
            return lifecycle
        }
    }

    this.state.observe(lifecycleOwner, stateObserver)
    this.events.observe(lifecycleOwner, eventsObserver)
    lifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    lifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

    observers(stateObserver, eventsObserver)

    lifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    lifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
}

/**
 * Runs the given lambda with three [MockObserver] instances attached
 * to the receiver [RainbowCakeViewModel] instance.
 *
 * The `stateObserver`, `eventsObserver`, and `queuedEventsObserver`
 * may be used to assert that the ViewModel's state changed to a
 * given value, or that it has emitted certain events.
 *
 * Usage example:
 *
 * ```
 * vm.observeStateAndEvents { stateObserver, eventsObserver, queuedEventsObserver ->
 *     vm.loadArticle(1L)
 *     stateObserver.assertObserved(Loading, ArticleLoaded())
 *
 *     vm.loadArticle(-1L)
 *     eventsObserver.assertObserved(InvalidIdError)
 * }
 * ```
 */
fun <VS : Any> RainbowCakeViewModel<VS>.observeStateAndEvents(
        observers: (stateObserver: MockObserver<VS>,
                    eventsObserver: MockObserver<OneShotEvent>,
                    queuedEventsObserver: MockObserver<QueuedOneShotEvent>) -> Unit
) {
    val stateObserver = MockLiveDataObserver<VS>()
    val eventsObserver = MockLiveDataObserver<OneShotEvent>()
    val queuedEventsObserver = MockLiveDataObserver<QueuedOneShotEvent>()

    val lifecycleOwner = object : LifecycleOwner {
        val lifecycle = LifecycleRegistry(this)
        override fun getLifecycle(): Lifecycle {
            return lifecycle
        }
    }

    this.state.observe(lifecycleOwner, stateObserver)
    this.events.observe(lifecycleOwner, eventsObserver)
    this.queuedEvents.observe(lifecycleOwner, queuedEventsObserver)
    lifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    lifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

    observers(stateObserver, eventsObserver, queuedEventsObserver)

    lifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    lifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
}
