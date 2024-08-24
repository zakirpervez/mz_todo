package com.example.mztodo.ui.screens.todo_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodoListScreen(onNavigate: () -> Unit) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            onNavigate()
        }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }, content = { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(text = "TODO List")
        }
    })
}

@Preview(showBackground = true, device = Devices.PIXEL_5)
@Composable
fun TodoListScreenPreview() {
    TodoListScreen {

    }
}