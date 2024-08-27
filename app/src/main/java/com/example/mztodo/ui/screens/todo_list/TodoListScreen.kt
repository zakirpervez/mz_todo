package com.example.mztodo.ui.screens.todo_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.common.composable.AddTodoText
import com.example.common.composable.ErrorText
import com.example.common.composable.TodoAppBar
import com.example.domain.entities.TodoItem
import com.example.mztodo.R
import com.example.mztodo.ui.screens.viewmodel.TodoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    todoListViewModel: TodoListViewModel = hiltViewModel(),
    navHostController: NavHostController? = null,
    onNavigate: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val errorMessage =
            navHostController?.currentBackStackEntry?.savedStateHandle?.get<String>("error") ?: ""
        if (errorMessage.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
        todoListViewModel.loadTodoItems()
    }

    val result = todoListViewModel.todoListState.value
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val lazyListState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()
    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Scaffold(topBar = {
        TodoAppBar(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            text = stringResource(id = R.string.app_name),
            scrollBehavior = scrollBehavior
        )
    }, floatingActionButton = {
        TodoListFloatingActionButton {
            onNavigate()
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
                ErrorText(
                    modifier = Modifier.align(Alignment.Center), errorMessage = result.errorMessage
                )
            }

            if (result.todoItems.isNullOrEmpty()) {
                AddTodoText(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.empty_todo_list)
                )
            } else {
                TodoListContent(
                    todoItems = result.todoItems,
                    isLandscape = isLandscape,
                    lazyListState = lazyListState,
                    lazyGridState = lazyGridState,
                    scrollBehavior = scrollBehavior
                )
            }
        }
    }
}

@Composable
fun TodoListFloatingActionButton(onNavigate: () -> Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        shape = CircleShape,
        onClick = onNavigate
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_todo_content_description)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListContent(
    todoItems: List<TodoItem>,
    isLandscape: Boolean,
    lazyListState: LazyListState,
    lazyGridState: LazyGridState,
    scrollBehavior: TopAppBarScrollBehavior
) {
    if (isLandscape) {
        LazyVerticalGrid(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            state = lazyGridState,
            contentPadding = PaddingValues(8.dp),
        ) {
            items(todoItems.size, key = { it }) {
                TodoItemView(todoItem = todoItems[it])
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize(),
            state = lazyListState
        ) {
            items(todoItems.size, key = { it }) {
                TodoItemView(todoItem = todoItems[it])
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
