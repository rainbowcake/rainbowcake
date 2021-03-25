package co.zsmb.rainbowcake.test.base

import co.zsmb.rainbowcake.test.CoroutineIODispatcherTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

/**
 * A base class for Presenter tests, where the [CoroutineIODispatcherTestRule]
 * has to be enabled to mock the IO dispatcher.
 */
@ExperimentalCoroutinesApi
public abstract class PresenterTest {

    @get:Rule
    public val ioDispatcherTestRule: CoroutineIODispatcherTestRule = CoroutineIODispatcherTestRule()

}
