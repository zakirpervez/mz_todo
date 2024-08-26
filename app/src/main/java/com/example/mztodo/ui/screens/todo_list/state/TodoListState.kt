package com.example.mztodo.ui.screens.todo_list.state

import com.example.domain.entities.TodoItem
data class TodoListState(
    var isLoading: Boolean = false,
    val todoItems: List<TodoItem>? = null,
    val errorMessage: String = ""
)
