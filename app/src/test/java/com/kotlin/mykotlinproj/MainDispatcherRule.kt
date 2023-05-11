package com.kotlin.mykotlinproj

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : InstantTaskExecutorRule() {
    override fun starting(description: Description) {
        super.starting(description)

        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)

        Dispatchers.resetMain()
    }
}

//@OptIn(ExperimentalCoroutinesApi::class)
//class MainDispatcherRule(
//    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
//) : InstantTaskExecutorRule() {
//    override fun starting(description: Description) {
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: Description) {
//        Dispatchers.resetMain()
//    }
//}

//@ExperimentalCoroutinesApi
//class MainCoroutineRule(
//    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
//) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(dispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        cleanupTestCoroutines()
//        Dispatchers.resetMain()
//    }
//}