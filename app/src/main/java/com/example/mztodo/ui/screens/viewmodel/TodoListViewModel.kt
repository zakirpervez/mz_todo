package com.example.mztodo.ui.screens.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Result
import com.example.domain.use_case.get_todo_item.GetTodoItemsUseCase
import com.example.mztodo.ui.screens.todo_list.state.TodoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodoItemsUseCase: GetTodoItemsUseCase
) : ViewModel() {
    private val _todoListState = mutableStateOf(TodoListState())
    val todoListState: State<TodoListState> = _todoListState

    init {
        loadTodoItems()
    }

    fun loadTodoItems() {
        getTodoItemsUseCase.invoke().onEach { result ->
            when (result) {
                is Result.Loading -> {
                    _todoListState.value = TodoListState(isLoading = true)
                }

                is Result.Success -> {
                    _todoListState.value = TodoListState(todoItems = result.data)
                }

                is Result.Failure -> {
                    _todoListState.value = TodoListState(errorMessage = result.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }
}
