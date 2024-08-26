package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.TodoDao
import com.example.data.database.entities.TodoItemEntity

@Database(entities = [TodoItemEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}
