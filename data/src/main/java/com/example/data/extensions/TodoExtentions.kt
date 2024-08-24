package com.example.data.extensions

import com.example.data.database.entities.TodoItemEntity
import com.example.domain.entities.TodoItem

fun TodoItem.toEntity(): TodoItemEntity {
    return TodoItemEntity(
        id = this.id,
        text = this.text
    )
}

fun TodoItemEntity.toDomain(): TodoItem {
    return TodoItem(
        id = this.id,
        text = this.text
    )
}
