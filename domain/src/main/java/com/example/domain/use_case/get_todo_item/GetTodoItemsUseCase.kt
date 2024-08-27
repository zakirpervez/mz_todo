package com.example.domain.use_case.get_todo_item

import com.example.domain.entities.Result
import com.example.domain.entities.TodoItem
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTodoItemsUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<Result<List<TodoItem>>> = flow {
        emit(Result.Loading)
        val todoItems = repository.getTodoItems()
        emit(Result.Success(todoItems))
    }.catch { exception ->
        val errorMessage: String = exception.message ?: "Something went wrong"
        emit(Result.Failure(errorMessage))
    }
}
