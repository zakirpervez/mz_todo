package com.example.mztodo.ui.screens.create_todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mztodo.R
import com.example.mztodo.ui.screens.viewmodel.CreateTodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoScreen(
    createTodoViewModel: CreateTodoViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val result by createTodoViewModel.createTodoState
    var text by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    LaunchedEffect(result) {
        if (result.errorMessage.isNotEmpty()) {
            onNavigate(result.errorMessage)
        } else if (result.status) {
            onNavigate("")
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.create_todo_title)) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            if (result.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                val buttonHeight = if (isLandscape) maxHeight * 0.2f else maxHeight * 0.064f
                val spacerHeight = maxHeight * 0.04f

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(if (isLandscape) 0.6f else 1f)
                        .height(if (isLandscape) maxHeight * 0.7f else 220.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center
                    ) {
                        TextField(modifier = Modifier.fillMaxWidth(),
                            value = text,
                            maxLines = 1,
                            onValueChange = { text = it },
                            label = { Text(stringResource(id = R.string.add_todo)) })
                        Spacer(modifier = Modifier.height(spacerHeight))
                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .height(buttonHeight)
                            .align(Alignment.CenterHorizontally), onClick = {
                            createTodoViewModel.addTodoItem(text)
                        }) {
                            Text(text = stringResource(id = R.string.add_todo))
                        }
                        Spacer(modifier = Modifier.height(spacerHeight))
                        if (result.errorMessage.isNotEmpty()) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = stringResource(id = R.string.todo_error),
                                color = Red,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(device = Devices.NEXUS_6)
@Composable
fun CreateTodoScreenPreview() {
    CreateTodoScreen {

    }
}