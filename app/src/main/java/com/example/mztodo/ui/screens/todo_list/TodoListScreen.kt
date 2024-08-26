package com.example.mztodo.ui.screens.todo_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entities.TodoItem
import com.example.mztodo.ui.screens.viewmodel.TodoListViewModel

@Composable
fun TodoListScreen(
    todoListViewModel: TodoListViewModel = hiltViewModel(), onNavigate: () -> Unit
) {

    LaunchedEffect(Unit) {
        todoListViewModel.loadTodoItems()
    }

    val result = todoListViewModel.todoListState.value
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            onNavigate()
        }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }) { paddingValues ->

        if (result.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        if (result.errorMessage.isNotEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(
                    text = result.errorMessage,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        if (result.todoItems.isNullOrEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(text = "No data found, response might be null or empty")
            }
        } else {
            LazyColumn {
                items(result.todoItems.size) {
                    TodoItemView(todoItem = result.todoItems[it])
                }
            }
        }
    }
}

@Composable
fun TodoItemView(todoItem: TodoItem) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Text(text = todoItem.text, modifier = Modifier.align(Alignment.CenterStart))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_5)
@Composable
fun TodoListScreenPreview() {
    TodoListScreen {

    }
}
