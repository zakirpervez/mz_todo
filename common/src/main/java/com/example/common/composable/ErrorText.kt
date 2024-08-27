package com.example.common.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorText(modifier: Modifier, errorMessage: String) {
    Text(
        text = errorMessage,
        color = Color.Red,
        modifier = modifier
    )
}