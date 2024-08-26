package com.example.domain.use_case.add_todo_item

import com.example.domain.entities.DataWrapper
import com.example.domain.entities.TodoItem
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTodoItemUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(todoItem: TodoItem): Flow<DataWrapper<Unit>> = flow {
        emit(DataWrapper.Loading)
        repository.addTodoItem(todoItem)
        emit(DataWrapper.Success(Unit))
    }.catch { exception ->
        val errorMessage = exception.message ?: "Something went wrong"
        emit(DataWrapper.Failure(errorMessage))
    }
}
