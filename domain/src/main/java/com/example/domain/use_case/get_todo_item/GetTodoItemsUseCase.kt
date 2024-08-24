package com.example.domain.use_case.get_todo_item

import com.example.domain.entities.TodoItem

interface GetTodoItemsUseCase {
    suspend operator fun invoke(): List<TodoItem>
}
