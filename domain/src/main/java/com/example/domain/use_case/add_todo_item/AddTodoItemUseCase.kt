package com.example.domain.use_case.add_todo_item

import com.example.domain.entities.TodoItem

interface AddTodoItemUseCase {
    suspend operator fun invoke(todoItem: TodoItem)
}
