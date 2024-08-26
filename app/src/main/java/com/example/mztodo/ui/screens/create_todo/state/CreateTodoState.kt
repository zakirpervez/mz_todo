package com.example.mztodo.ui.screens.create_todo.state

data class CreateTodoState(
    var isLoading: Boolean = false,
    var status: Boolean = false,
    val errorMessage: String = ""
)
