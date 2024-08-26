package com.example.domain.use_case.get_todo_item

import com.example.domain.entities.DataWrapper
import com.example.domain.entities.TodoItem
import com.example.domain.repository.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetTodoItemsUseCaseTest {
    private lateinit var repository: Repository
    private lateinit var getTodoItemsUseCase: GetTodoItemsUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        getTodoItemsUseCase = GetTodoItemsUseCase(repository)
    }

    @Test
    fun `invoke should emit Loading and Success with a list of TodoItems when repository call is successful`() =
        runBlocking {
            val todoItem1 = TodoItem(id = 1, text = "Test Todo 1", isCompleted = false)
            val todoItem2 = TodoItem(id = 2, text = "Test Todo 2", isCompleted = false)
            val todoItems = listOf(todoItem1, todoItem2)

            coEvery { repository.getTodoItems() } returns todoItems

            val result = mutableListOf<DataWrapper<List<TodoItem>>>()
            val flow = getTodoItemsUseCase()
            flow.toList(result)

            assertEquals(2, result.size)
            assertTrue(result[0] is DataWrapper.Loading)
            assertTrue(result[1] is DataWrapper.Success)
            assertEquals(todoItems, (result[1] as DataWrapper.Success).data)
            coVerify { repository.getTodoItems() }
        }

    @Test
    fun `invoke should emit Loading and Failure when repository call throws an exception`() =
        runBlocking {
            val exceptionMessage = "Something went wrong"
            coEvery { repository.getTodoItems() } throws Exception(exceptionMessage)

            val result = mutableListOf<DataWrapper<List<TodoItem>>>()
            val flow = getTodoItemsUseCase()
            flow.toList(result)

            assertEquals(2, result.size)
            assertTrue(result[0] is DataWrapper.Loading)
            assertTrue(result[1] is DataWrapper.Failure)
            assertEquals(exceptionMessage, (result[1] as DataWrapper.Failure).errorMessage)
            coVerify { repository.getTodoItems() }
        }

    @Test
    fun `invoke should emit Loading and Failure with default message when repository call throws an exception without a message`() = runBlocking {
        coEvery { repository.getTodoItems() } throws Exception()

        val result = mutableListOf<DataWrapper<List<TodoItem>>>()
        val flow = getTodoItemsUseCase()
        flow.toList(result)

        assertEquals(2, result.size)
        assertTrue(result[0] is DataWrapper.Loading)
        assertTrue(result[1] is DataWrapper.Failure)
        assertEquals("Something went wrong", (result[1] as DataWrapper.Failure).errorMessage)
        coVerify { repository.getTodoItems() }
    }
}