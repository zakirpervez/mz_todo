package com.example.domain.entities

sealed class DataWrapper<out T> {
    data object Loading : DataWrapper<Nothing>()
    data class Success<out T>(val data: T) : DataWrapper<T>()
    data class Failure(val errorMessage: String) : DataWrapper<Nothing>()
}
