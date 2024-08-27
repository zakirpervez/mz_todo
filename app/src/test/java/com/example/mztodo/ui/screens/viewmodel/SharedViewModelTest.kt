package com.example.mztodo.ui.screens.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SharedViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SharedViewModel

    @Before
    fun setUp() {
        viewModel = SharedViewModel()
    }

    @Test
    fun `setResult should update result state`() {
        val expectedMessage = "Test Message"
        viewModel.setResult(expectedMessage)
        val resultState: String? = viewModel.result.value
        assertEquals(expectedMessage, resultState)
    }

    @Test
    fun `clearResult should set result state to null`() {
        viewModel.setResult("Some Message")
        viewModel.clearResult()
        val resultState: String? = viewModel.result.value
        assertEquals(null, resultState)
    }
}
