package com.example.data.di

import android.content.Context
import com.example.data.database.TodoDatabase
import com.example.data.database.dao.TodoDao
import com.example.data.database.provider.DatabaseProvider
import com.example.data.repository.TodoRepository
import com.example.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun providerTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return DatabaseProvider.getInstance(context)
    }

    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.getTodoDao()
    }

    @Provides
    fun provideTodoRepository(todoDao: TodoDao): Repository {
        return TodoRepository(todoDao)
    }
}
