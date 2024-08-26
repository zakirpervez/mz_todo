package com.example.mztodo.ui.screens.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.DataWrapper
import com.example.domain.entities.TodoItem
import com.example.domain.use_case.add_todo_item.AddTodoItemUseCase
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

    fun loadTodoItems() {
        getTodoItemsUseCase.invoke().onEach { result ->
            when (result) {
                is DataWrapper.Loading -> {
                    _todoListState.value = TodoListState(isLoading = true)
                }

                is DataWrapper.Success -> {
                    _todoListState.value = TodoListState(todoItems = result.data)
                }

                is DataWrapper.Failure -> {
                    _todoListState.value = TodoListState(errorMessage = result.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }
}
