package com.example.mztodo.ui.screens.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entities.DataWrapper
import com.example.domain.entities.TodoItem
import com.example.domain.use_case.get_todo_item.GetTodoItemsUseCase
import com.example.mztodo.ui.screens.todo_list.state.TodoListState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class TodoListViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

//    private lateinit var viewModel: TodoListViewModel
//    private lateinit var getTodoItemsUseCase: GetTodoItemsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
//        getTodoItemsUseCase = mockk()
//        viewModel = TodoListViewModel(getTodoItemsUseCase)
    }

    @Test
    fun `loadTodoItems should update todoListState with success state`() = runBlockingTest {
        val todoItem1 = TodoItem(id = 1, text = "Test Todo 1", isCompleted = false)
        val todoItem2 = TodoItem(id = 2, text = "Test Todo 2", isCompleted = false)
        val todoItems = listOf(todoItem1, todoItem2)

        val flow = flow {
            emit(DataWrapper.Success(todoItems))
        }

        val useCase: GetTodoItemsUseCase = mockk()

        coEvery { useCase.invoke() } returns flow

        val viewModel = TodoListViewModel(useCase)
        viewModel.loadTodoItems()

        advanceUntilIdle()
        val successState: TodoListState = viewModel.todoListState.value
        assertEquals(false, successState.isLoading)
        assertEquals(todoItems, successState.todoItems)
        assertEquals("", successState.errorMessage)
    }

    @Test
    fun `loadTodoItems should update todoListState with failure state`() = runBlockingTest {
        val flow = flow<DataWrapper<List<TodoItem>>> {
            emit(DataWrapper.Failure("Error occurred"))
        }

        val useCase: GetTodoItemsUseCase = mockk()
        coEvery { useCase.invoke() } returns flow

        val viewModel = TodoListViewModel(useCase)
        viewModel.loadTodoItems()

        advanceUntilIdle()
        val failureState: TodoListState = viewModel.todoListState.value
        assertEquals(false, failureState.isLoading)
        assertEquals(null, failureState.todoItems)
        assertEquals("Error occurred", failureState.errorMessage)
    }
}