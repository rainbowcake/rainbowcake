package co.zsmb.rainbowcake.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import co.zsmb.rainbowcake.internal.logging.log

/**
 * Base class for Activities that connects them to the appropriate ViewModel instances.
 */
public abstract class RainbowCakeActivity<VS : Any, VM : RainbowCakeViewModel<VS>> : AppCompatActivity() {

    private val logTag: String by lazy(mode = LazyThreadSafetyMode.NONE) { "RainbowCakeActivity ($this)" }

    /**
     * The ViewModel of this Activity.
     */
    protected lateinit var viewModel: VM

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        viewModel.state.observe(this) { viewState ->
            viewState?.let { render(it) }
        }
        viewModel.events.observe(this) { event ->
            event?.let { onEvent(it) }
        }
        viewModel.queuedEvents.observe(this) { event ->
            event?.let { onEvent(it) }
        }
    }

    /**
     * This method should return a ViewModel instance for the current Fragment.
     *
     * If one of RainbowCake's own DI libraries are being used, this method should
     * return the result of a [getViewModelFromFactory] call.
     */
    public abstract fun provideViewModel(): VM

    /**
     * Renders the view state. Called when the view state changes and the UI should be
     * updated accordingly.
     *
     * Must be implemented in a way so that previous view states do not affect the current
     * state of the UI. In other words, the same view state being set must always result
     * in the same state for the displayed UI.
     */
    public abstract fun render(viewState: VS)

    /**
     * Handles one-time events emitted by the ViewModel.
     */
    public open fun onEvent(event: OneShotEvent) {
        log(logTag, "Unhandled event: $event")
    }

}

