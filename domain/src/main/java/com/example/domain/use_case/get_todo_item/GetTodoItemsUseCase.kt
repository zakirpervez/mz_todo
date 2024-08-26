package com.example.domain.use_case.get_todo_item

import com.example.domain.entities.DataWrapper
import com.example.domain.entities.TodoItem
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetTodoItemsUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<DataWrapper<List<TodoItem>>> = flow {
        emit(DataWrapper.Loading)
        val todoItems = repository.getTodoItems()
        emit(DataWrapper.Success(todoItems))
    }.catch { exception ->
        val errorMessage: String = exception.message ?: "Something went wrong"
        emit(DataWrapper.Failure(errorMessage))
    }
}
