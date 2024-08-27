package com.example.mztodo.ui.screens.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Result
import com.example.domain.entities.TodoItem
import com.example.domain.use_case.add_todo_item.AddTodoItemUseCase
import com.example.mztodo.ui.screens.create_todo.state.CreateTodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.delayFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTodoViewModel @Inject constructor(private val addTodoItemUseCase: AddTodoItemUseCase) :
    ViewModel() {
    private val _createTodoState = mutableStateOf(CreateTodoState())
    val createTodoState: State<CreateTodoState> = _createTodoState

    fun addTodoItem(text: String) {
        viewModelScope.launch {
            _createTodoState.value = CreateTodoState(isLoading = true)
            if (text == "Error") {
                delay(3000)
                _createTodoState.value = CreateTodoState(errorMessage = "Failed to add TODO")
            } else {
                addTodoItemUseCase.invoke(TodoItem(text = text)).collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            _createTodoState.value = CreateTodoState(isLoading = true)
                        }

                        is Result.Success -> {
                            _createTodoState.value = CreateTodoState(status = true)
                        }

                        is Result.Failure -> {
                            _createTodoState.value =
                                CreateTodoState(errorMessage = result.errorMessage)
                        }
                    }
                }
            }
        }
    }
}
