package com.example.domain.entities

data class TodoItem(
    val id: Int? = null, val text: String, val isCompleted: Boolean = false
)
