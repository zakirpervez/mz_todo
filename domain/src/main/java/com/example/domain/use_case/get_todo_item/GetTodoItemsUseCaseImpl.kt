package com.example.domain.use_case.get_todo_item

import com.example.domain.entities.TodoItem
import com.example.domain.repository.Repository

class GetTodoItemsUseCaseImpl(private val repository: Repository) : GetTodoItemsUseCase {
    override suspend fun invoke(): List<TodoItem> {
        return repository.getTodoItems()
    }
}