package hu.autsoft.rainbowcake.navigation

import android.os.Bundle
import android.support.annotation.CallSuper
import hu.autsoft.rainbowcake.R
import hu.autsoft.rainbowcake.base.BaseActivity
import hu.autsoft.rainbowcake.base.BaseViewModel

/**
 * Activity base class with built-in Fragment based navigation support.
 */
abstract class NavActivity<VS : Any, VM : BaseViewModel<VS>> : BaseActivity<VS, VM>() {

    /**
     * The Navigator that subclasses can access to perform navigation actions.
     */
    val navigator: ExtendedNavigator
        get() = navigatorImpl

    /**
     * Private implementation of the Navigator interface with a more concrete
     * type for back press delegation.
     */
    private lateinit var navigatorImpl: NavigatorImpl

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigatorImpl = NavigatorImpl(this)
    }

    @CallSuper
    override fun onBackPressed() = navigatorImpl.onBackPressed()

}
