package com.example.domain.use_case.add_todo_item

import com.example.domain.entities.Result
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

class AddTodoItemUseCaseTest {
    private lateinit var repository: Repository
    private lateinit var addTodoItemUseCase: AddTodoItemUseCase

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        addTodoItemUseCase = AddTodoItemUseCase(repository)
    }

    @Test
    fun `invoke should emit Loading and Success when repository call is successful`() =
        runBlocking {
            val todoItem = TodoItem(id = 1, text = "Test Todo", isCompleted = false)
            coEvery { repository.addTodoItem(todoItem) } returns Unit

            val result = mutableListOf<Result<Unit>>()
            val flow = addTodoItemUseCase(todoItem)
            flow.toList(result)

            assertEquals(2, result.size)
            assertTrue(result[0] is com.example.domain.entities.DataWrapper.Result.Loading)
            assertTrue(result[1] is com.example.domain.entities.DataWrapper.Result.Success)
            coVerify { repository.addTodoItem(todoItem) }
        }

    @Test
    fun `invoke should emit Loading and Failure when repository call throws exception`() =
        runBlocking {
            val todoItem = TodoItem(id = 1, text = "Test Todo", isCompleted = false)
            val exceptionMessage = "Something went wrong"
            coEvery { repository.addTodoItem(todoItem) } throws Exception(exceptionMessage)

            val result = mutableListOf<Result<Unit>>()
            val flow = addTodoItemUseCase(todoItem)
            flow.toList(result)

            assertEquals(2, result.size)
            assertTrue(result[0] is com.example.domain.entities.DataWrapper.Result.Loading)
            assertTrue(result[1] is com.example.domain.entities.DataWrapper.Result.Failure)
            assertEquals(exceptionMessage, (result[1] as com.example.domain.entities.DataWrapper.Result.Failure).errorMessage)
            coVerify { repository.addTodoItem(todoItem) }
        }
}