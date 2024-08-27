package com.example.mztodo.ui.screens.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entities.Result
import com.example.domain.use_case.add_todo_item.AddTodoItemUseCase
import com.example.mztodo.ui.screens.create_todo.state.CreateTodoState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CreateTodoViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        // Set the main dispatcher to a TestCoroutineDispatcher
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `addTodoItem should update createTodoState with success state`() = runBlocking {
        val addTodoItemUseCase: AddTodoItemUseCase = mockk()

        coEvery { addTodoItemUseCase.invoke(any()) } returns flow {
            emit(Result.Success(Unit))
        }

        val viewModel = CreateTodoViewModel(addTodoItemUseCase)
        viewModel.addTodoItem("Test Todo")

        val successState: CreateTodoState = viewModel.createTodoState.value
        assertEquals(false, successState.isLoading)
        assertEquals("", successState.errorMessage)
        assertEquals(true, successState.status)
    }

    @Test
    fun `addTodoItem should update createTodoState with failure state`() = runBlocking {
        val errorMessage = "Error occurred"
        val addTodoItemUseCase: AddTodoItemUseCase = mockk()
        coEvery { addTodoItemUseCase.invoke(any()) } returns flow {
            emit(Result.Failure(errorMessage))
        }

        val viewModel = CreateTodoViewModel(addTodoItemUseCase)
        viewModel.addTodoItem("Test Todo")

        val failureState: CreateTodoState = viewModel.createTodoState.value
        assertEquals(false, failureState.isLoading)
        assertEquals(errorMessage, failureState.errorMessage)
        assertEquals(false, failureState.status)
    }

    @Test
    fun `addTodoItem should handle error text and update createTodoState with loading and error message`() =
        runBlocking {
            val dispatcher = TestCoroutineDispatcher()
            Dispatchers.setMain(dispatcher)
            val addTodoItemUseCase: AddTodoItemUseCase = mockk()
            val viewModel = CreateTodoViewModel(addTodoItemUseCase)
            viewModel.addTodoItem("Error")

            // Assert loading state
            val loadingState: CreateTodoState = viewModel.createTodoState.value
            assertEquals(true, loadingState.isLoading)
            assertEquals("", loadingState.errorMessage)
            assertEquals(false, loadingState.status)

            // Advance time by 3000ms to simulate delay
            dispatcher.scheduler.apply { advanceTimeBy(3000); runCurrent() }

            // Assert error state after delay
            val errorState: CreateTodoState = viewModel.createTodoState.value
            assertEquals(false, errorState.isLoading)
            assertEquals("Failed to add TODO", errorState.errorMessage)
            assertEquals(false, errorState.status)

            dispatcher.cleanupTestCoroutines()
        }
}