package com.example.mztodo.ui.screens.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.TodoItem
import com.example.domain.use_case.add_todo_item.AddTodoItemUseCase
import com.example.domain.use_case.get_todo_item.GetTodoItemsUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    private val addTodoItemUseCase: AddTodoItemUseCase,
    private val getTodoItemsUseCase: GetTodoItemsUseCase
) : ViewModel() {
    var todoItems by mutableStateOf<List<TodoItem>>(emptyList())
        private set

    fun addTodoItem(text: String) {
        viewModelScope.launch {
            addTodoItemUseCase(TodoItem(text = text))
            loadTodoItems()
        }
    }

    fun loadTodoItems() {
        viewModelScope.launch {
            todoItems = getTodoItemsUseCase()
        }
    }
}
