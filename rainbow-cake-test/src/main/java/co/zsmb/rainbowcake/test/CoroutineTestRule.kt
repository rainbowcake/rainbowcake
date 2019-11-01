package co.zsmb.rainbowcake.test

import co.zsmb.rainbowcake.ioContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * A JUnit 4 test rule to be used in ViewModel and Presenter tests.
 *
 * Forces coroutine code that otherwise uses the Main or IO dispatchers
 * to run on the single test thread.
 */
@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                // setup
                @Suppress("DEPRECATION")
                ioContext = testDispatcher
                Dispatchers.setMain(testDispatcher)

                // run test
                base.evaluate()

                // teardown
                @Suppress("DEPRECATION")
                ioContext = Dispatchers.IO
                Dispatchers.resetMain()
                testDispatcher.cleanupTestCoroutines()
            }
        }
    }

}
