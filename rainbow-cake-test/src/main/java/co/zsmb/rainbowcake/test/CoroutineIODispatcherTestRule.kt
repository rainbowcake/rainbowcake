package co.zsmb.rainbowcake.test

import co.zsmb.rainbowcake.internal.InternalRainbowCakeApi
import co.zsmb.rainbowcake.ioContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * A JUnit 4 test rule to be used in ViewModel and Presenter tests.
 *
 * Forces coroutine code that otherwise uses the IO dispatcher to
 * run on the single test thread.
 */
@ExperimentalCoroutinesApi
public class CoroutineIODispatcherTestRule : TestRule {

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @OptIn(InternalRainbowCakeApi::class)
    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                // setup
                @Suppress("DEPRECATION")
                ioContext = testDispatcher

                // run test
                base.evaluate()

                // teardown
                @Suppress("DEPRECATION")
                ioContext = Dispatchers.IO
                testDispatcher.cleanupTestCoroutines()
            }
        }
    }

}
