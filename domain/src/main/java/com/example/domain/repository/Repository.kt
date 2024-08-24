package com.example.domain.repository

import com.example.domain.entities.TodoItem

interface Repository {
    suspend fun addTodoItem(todoItem: TodoItem)
    suspend fun getTodoItems(): List<TodoItem>
}
