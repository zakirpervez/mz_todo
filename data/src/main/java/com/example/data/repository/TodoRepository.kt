package com.example.data.repository

import com.example.data.database.dao.TodoDao
import com.example.data.extensions.toDomain
import com.example.data.extensions.toEntity
import com.example.domain.entities.TodoItem
import com.example.domain.repository.Repository

class TodoRepository(private val todoDao: TodoDao) : Repository {
    override suspend fun addTodoItem(todoItem: TodoItem) {
        todoDao.insert(todoItem.toEntity())
    }

    override suspend fun getTodoItems(): List<TodoItem> {
        return todoDao.getAllTodoItems().map { item -> item.toDomain() }
    }
}
