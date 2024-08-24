package com.example.domain.use_case.add_todo_item

import com.example.domain.entities.TodoItem
import com.example.domain.repository.Repository

class AddTodoItemUseCaseImpl(private val repository: Repository) : AddTodoItemUseCase {
    override suspend fun invoke(todoItem: TodoItem) {
        repository.addTodoItem(todoItem)
    }
}