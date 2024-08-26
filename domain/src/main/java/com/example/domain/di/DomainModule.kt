package com.example.domain.di

import com.example.domain.repository.Repository
import com.example.domain.use_case.add_todo_item.AddTodoItemUseCase
import com.example.domain.use_case.get_todo_item.GetTodoItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideAddTodoUseCase(repository: Repository): AddTodoItemUseCase {
        return AddTodoItemUseCase(repository)
    }

    @Provides
    fun provideGetTodoItemsUseCase(repository: Repository): GetTodoItemsUseCase {
        return GetTodoItemsUseCase(repository)
    }
}
