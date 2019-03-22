package co.zsmb.rainbowcake.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.AnimRes
import android.support.annotation.AnimatorRes
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import co.zsmb.rainbowcake.base.ViewModelScope.Activity
import co.zsmb.rainbowcake.base.ViewModelScope.Default
import co.zsmb.rainbowcake.base.ViewModelScope.ParentFragment

/**
 * Base class for Fragments that connects them to the appropriate ViewModel instances.
 */
abstract class RainbowCakeFragment<VS : Any, VM : RainbowCakeViewModel<VS>> : InjectedFragment() {

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
     *
     * @param viewState The new state of the ViewModel.
     */
    abstract fun render(viewState: VS)

    /**
     * Handles one-time events emitted by the ViewModel.
     *
     * @param event An event emitted by the ViewModel.
     */
    open fun onEvent(event: OneShotEvent) {}

    /**
     * Returns the ID of the Fragment's layout resource
     */
    @LayoutRes
    abstract fun getViewResource(): Int

    /**
     * Extension point for navigation addon library. Do not use this yourself.
     */
    @AnimRes
    @AnimatorRes
    @set:JvmName("overrideAnimation")
    internal var overrideAnimation: Int? = null

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

/**
 * Uses the ViewModelFactory in the receiver [RainbowCakeFragment] to fetch the appropriate
 * ViewModel instance for the Fragment.
 *
 * @param scope The scope that the ViewModel should be fetched from and exist in.
 *              See [ViewModelScope] for details.
 */
inline fun <F : RainbowCakeFragment<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> F.getViewModelFromFactory(
        scope: ViewModelScope = Default
): VM {
    return when (scope) {
        Default -> {
            ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
        }
        is ParentFragment -> {
            val parentFragment = getParentFragment() ?: throw IllegalStateException("No parent Fragment")
            if (scope.key != null) {
                ViewModelProviders.of(parentFragment, viewModelFactory).get(scope.key, VM::class.java)
            } else {
                ViewModelProviders.of(parentFragment, viewModelFactory).get(VM::class.java)
            }
        }
        is Activity -> {
            if (scope.key != null) {
                ViewModelProviders.of(requireActivity(), viewModelFactory).get(scope.key, VM::class.java)
            } else {
                ViewModelProviders.of(requireActivity(), viewModelFactory).get(VM::class.java)
            }
        }
    }
}
