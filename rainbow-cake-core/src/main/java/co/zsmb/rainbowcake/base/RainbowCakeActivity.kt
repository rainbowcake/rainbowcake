package co.zsmb.rainbowcake.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import co.zsmb.rainbowcake.internal.logging.log

/**
 * Base class for Activities that connects them to the appropriate ViewModel instances.
 */
abstract class RainbowCakeActivity<VS : Any, VM : RainbowCakeViewModel<VS>> : AppCompatActivity() {

    private val logTag: String by lazy(mode = LazyThreadSafetyMode.NONE) { "RainbowCakeActivity ($this)" }

    /**
     * The ViewModel of this Activity.
     */
    protected lateinit var viewModel: VM

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        viewModel.state.observe(this, Observer { viewState ->
            viewState?.let { render(it) }
        })
        viewModel.events.observe(this, Observer { event ->
            event?.let { onEvent(it) }
        })
        viewModel.queuedEvents.observe(this, Observer { event ->
            event?.let { onEvent(it) }
        })
    }

    /**
     * This method should return a ViewModel instance for the current Fragment.
     *
     * If one of RainbowCake's own DI libraries are being used, this method should
     * return the result of a [getViewModelFromFactory] call.
     */
    abstract fun provideViewModel(): VM

    /**
     * Renders the view state. Called when the view state changes and the UI should be
     * updated accordingly.
     *
     * Must be implemented in a way so that previous view states do not affect the current
     * state of the UI. In other words, the same view state being set must always result
     * in the same state for the displayed UI.
     */
    abstract fun render(viewState: VS)

    /**
     * Handles one-time events emitted by the ViewModel.
     */
    open fun onEvent(event: OneShotEvent) {
        log(logTag, "Unhandled event: $event")
    }

}

