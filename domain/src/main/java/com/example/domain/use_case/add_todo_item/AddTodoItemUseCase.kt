package com.example.domain.use_case.add_todo_item

import com.example.domain.entities.Result
import com.example.domain.entities.TodoItem
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTodoItemUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(todoItem: TodoItem): Flow<Result<Unit>> = flow {
        emit(Result.Loading)
        repository.addTodoItem(todoItem)
        emit(Result.Success(Unit))
    }.catch { exception ->
        val errorMessage = exception.message ?: "Something went wrong"
        emit(Result.Failure(errorMessage))
    }
}
