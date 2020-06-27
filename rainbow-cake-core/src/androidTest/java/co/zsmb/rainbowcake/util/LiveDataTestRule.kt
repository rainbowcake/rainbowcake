package co.zsmb.rainbowcake.util

import co.zsmb.rainbowcake.internal.livedata.MainThreadWrapper
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor

class LiveDataTestRule : TestRule {

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                // setup
                MainThreadWrapper.executor = Executor { it.run() }

                // run test
                base.evaluate()

                // teardown
                MainThreadWrapper.resetExecutor()
            }
        }
    }

}
