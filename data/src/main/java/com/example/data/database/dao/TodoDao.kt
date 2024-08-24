package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.TodoItemEntity

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoItem: TodoItemEntity)

    @Query("SELECT * FROM todo_items")
    suspend fun getAllTodoItems(): List<TodoItemEntity>
}
