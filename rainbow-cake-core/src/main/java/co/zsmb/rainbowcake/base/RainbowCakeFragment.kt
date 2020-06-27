package co.zsmb.rainbowcake.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import co.zsmb.rainbowcake.internal.logging.log

/**
 * Base class for Fragments that connects them to the appropriate ViewModel instances.
 */
abstract class RainbowCakeFragment<VS : Any, VM : RainbowCakeViewModel<VS>> : Fragment() {

    private val logTag: String by lazy(mode = LazyThreadSafetyMode.NONE) { "RainbowCakeFragment ($this)" }

    /**
     * The ViewModel of this Fragment.
     */
    protected lateinit var viewModel: VM

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()

        viewModel.events.observe(this, Observer { event ->
            event?.let { onEvent(it) }
        })
        viewModel.queuedEvents.observe(this, Observer { event ->
            event?.let { onEvent(it) }
        })
    }

    /**
     * Calls to super for this method MAY be omitted if the View inflation needs
     * to be customized. In these cases, [getViewResource] can return any value
     * as it won't be used (recommendation: 0).
     */
    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getViewResource(), container, false)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, Observer { viewState ->
            viewState?.let { render(it) }
        })
    }

    /**
     * This method should return a ViewModel instance for the current Fragment.
     *
     * If one of RainbowCake's own DI libraries are being used, this method should
     * return the result of a [getViewModelFromFactory] call.
     */
    protected abstract fun provideViewModel(): VM

    /**
     * Renders the view state. Called when the view state changes and the UI should be
     * updated accordingly.
     *
     * Must be implemented in a way so that previous view states do not affect the current
     * state of the UI. In other words, the same view state being set must always result
     * in the same state for the displayed UI.
     *
     * @param viewState The new state of the ViewModel.
     */
    protected abstract fun render(viewState: VS)

    /**
     * Handles one-time events emitted by the ViewModel.
     *
     * @param event An event emitted by the ViewModel.
     */
    protected open fun onEvent(event: OneShotEvent) {
        log(logTag, "Unhandled event: $event")
    }

    /**
     * Returns the ID of the Fragment's layout resource. If you need custom inflation logic
     * for your Fragment, besides choosing a layout resource to inflate, override the
     * [onViewCreated] method.
     */
    @LayoutRes
    protected abstract fun getViewResource(): Int

    /**
     * Extension point for navigation addon library. Do not use this yourself.
     */
    @AnimRes
    @AnimatorRes
    @set:JvmName("overrideAnimation")
    internal var overrideAnimation: Int? = null

    @CallSuper
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        overrideAnimation?.let { override ->
            val animation = if (override != 0) {
                AnimationUtils.loadAnimation(context, override)
            } else {
                object : Animation() {
                    init {
                        duration = 0
                    }
                }
            }

            overrideAnimation = null

            return animation
        }
        return null
    }

}
