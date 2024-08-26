package com.example.mztodo.ui.screens.todo_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entities.TodoItem
import com.example.mztodo.R
import com.example.mztodo.ui.screens.viewmodel.TodoListViewModel
import com.example.mztodo.ui.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    todoListViewModel: TodoListViewModel = hiltViewModel(), onNavigate: () -> Unit
) {
    LaunchedEffect(Unit) {
        todoListViewModel.loadTodoItems()
    }

    val result = todoListViewModel.todoListState.value
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val lazyListState = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        )
    }, floatingActionButton = {
        FloatingActionButton(containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape,
            onClick = {
                onNavigate()
            }) {
            Icon(
                Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_todo_content_description)
            )
        }
    }) { paddingValues ->

        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (result.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (result.errorMessage.isNotEmpty()) {
                Text(
                    text = result.errorMessage, modifier = Modifier.align(Alignment.Center)
                )
            }

            if (result.todoItems.isNullOrEmpty()) {
                Text(
                    text = stringResource(id = R.string.empty_todo_list),
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Black
                )
            } else {
                if (isLandscape) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 3 columns in landscape mode
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(result.todoItems.size) {
                            TodoItemView(todoItem = result.todoItems[it])
                        }
                    }
                } else {
                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                            .fillMaxSize()
                    ) {
                        items(result.todoItems.size) {
                            TodoItemView(todoItem = result.todoItems[it])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TodoItemView(todoItem: TodoItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Adjusted height for a more card-like appearance
        elevation = CardDefaults.cardElevation(4.dp) // Adjust elevation as needed
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp) // Padding inside the card
        ) {
            Text(text = todoItem.text)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_5)
@Composable
fun TodoListScreenPreview() {
    TodoListScreen {

    }
}
