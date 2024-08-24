package com.example.data.database.provider

import android.content.Context
import androidx.room.Room
import com.example.data.database.TodoDatabase

object DatabaseProvider {
    @Volatile
    private var instance: TodoDatabase? = null

    fun getInstance(context: Context): TodoDatabase {
        return instance ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext, TodoDatabase::class.java, "todo_database"
            ).build()
        }
    }
}