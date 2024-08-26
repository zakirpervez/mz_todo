package com.example.data.repository

import com.example.data.database.dao.TodoDao
import com.example.data.database.entities.TodoItemEntity
import com.example.data.extensions.toEntity
import com.example.domain.entities.TodoItem
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class TodoRepositoryTest {

    private lateinit var todoDao: TodoDao
    private lateinit var todoRepository: TodoRepository

    @Before
    fun setUp() {
        todoDao = mockk()
        todoRepository = TodoRepository(todoDao)
    }

    @Test
    fun `addTodoItem should call todoDao insert method`() = runBlocking {
        val todoItem = TodoItem(id = 1, text = "Test Todo", isCompleted = false)
        val todoEntity = todoItem.toEntity()
        coEvery { todoDao.insert(any()) } just Runs
        todoRepository.addTodoItem(todoItem)
        coVerify { todoDao.insert(todoEntity) }
    }

    @Test
    fun `getTodoItems should return a list of TodoItems`() = runBlocking {
        // Arrange
        val todoItem1 = TodoItemEntity(id = 1, text = "Test Todo", isCompleted = false)
        val todoItem2 = TodoItemEntity(id = 2, text = "Test Todo", isCompleted = false)
        val todoItem3 = TodoItemEntity(id = 3, text = "Test Todo", isCompleted = false)
        val todoItems = listOf(todoItem1, todoItem2, todoItem3)

        coEvery { todoDao.getAllTodoItems() } returns todoItems

        val result = todoRepository.getTodoItems()
        assertEquals(todoItems.size, result.size)
        assertTrue(result.all { it is TodoItem })
    }
}
