package hu.autsoft.rainbowcake.base

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
import hu.autsoft.rainbowcake.navigation.NavigatorImpl
import hu.autsoft.rainbowcake.navigation.NoAnimation

/**
 * Base class for Fragments that connects them to the appropriate ViewModel instances.
 */
abstract class BaseFragment<VS : Any, VM : BaseViewModel<VS>> : InjectedFragment() {

    /**
     * The ViewModel of this Fragment.
     */
    protected lateinit var viewModel: VM

    /**
     * Calls to super for this method may be omitted if the View inflation needs
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
        viewModel = provideViewModel()

        viewModel.state.observe(viewLifecycleOwner, Observer { viewState ->
            viewState?.let { render(it) }
        })
        viewModel.events.observe(viewLifecycleOwner, Observer { event ->
            event?.let { onEvent(it) }
        })
    }

    /**
     * This method MUST (see RFC 2119) always return the result of the [getViewModelFromFactory] call.
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

    /**
     * Returns the ID of the Fragment's layout resource
     */
    @LayoutRes
    abstract fun getViewResource(): Int

    /**
     * Implementation detail of navigation support. [NavigatorImpl] sets this property
     * when the next animation to be created by this Fragment needs to be overridden.
     */
    @AnimRes
    @AnimatorRes
    internal var overrideAnimation: Int? = null

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        overrideAnimation?.let { override ->
            val animation = if (override != 0) {
                AnimationUtils.loadAnimation(context, override)
            } else {
                NoAnimation
            }

            overrideAnimation = null

            return animation
        }
        return null
    }

}

/**
 * Uses the ViewModelFactory in the receiver [BaseFragment] to fetch the appropriate
 * ViewModel instance for the Fragment.
 */
inline fun <F : BaseFragment<VS, VM>, VS, reified VM : BaseViewModel<VS>> F.getViewModelFromFactory(
        key: String? = null
): VM {
    return if (key == null) {
        ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
    } else {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(key, VM::class.java)
    }
}
