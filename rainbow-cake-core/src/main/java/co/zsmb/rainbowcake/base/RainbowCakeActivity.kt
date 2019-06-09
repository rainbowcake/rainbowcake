package co.zsmb.rainbowcake.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity

/**
 * Base class for Activities that connects them to the appropriate ViewModel instances.
 */
abstract class RainbowCakeActivity<VS : Any, VM : RainbowCakeViewModel<VS>> : AppCompatActivity() {

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
    }

    /**
     * This method MUST (as in RFC 2119 MUST) always return the result of the
     * [getViewModelFromFactory] call.
     *
     * This is a requirement because the base class can't refer to the concrete ViewModel
     * type with a reified parameter.
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
    open fun onEvent(event: OneShotEvent) {}

}

