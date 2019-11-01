package co.zsmb.rainbowcake.test.base

import co.zsmb.rainbowcake.test.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

/**
 * A base class for Presenter tests, where the [CoroutineTestRule]
 * has to be enabled to mock the IO dispatcher.
 */
@ExperimentalCoroutinesApi
abstract class PresenterTest {

    @get:Rule
    var coroutineRule = CoroutineTestRule()

}
