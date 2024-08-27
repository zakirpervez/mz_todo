package com.example.domain.entities

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val errorMessage: String) : Result<Nothing>()
}
