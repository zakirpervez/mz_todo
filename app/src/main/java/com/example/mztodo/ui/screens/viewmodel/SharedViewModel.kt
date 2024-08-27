package com.example.mztodo.ui.screens.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _result = mutableStateOf<String?>(null)
    val result: State<String?> = _result

    fun setResult(message: String) {
        _result.value = message
    }

    fun clearResult() {
        _result.value = null
    }
}
