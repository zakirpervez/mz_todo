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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    createTodoViewModel: CreateTodoViewModel = hiltViewModel(), onNavigate: () -> Unit
) {
    var text by remember { mutableStateOf("") }

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
            val buttonHeight = maxHeight * 0.064f
            val spacerHeight = maxHeight * 0.04f
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center
                ) {
                    TextField(modifier = Modifier.fillMaxWidth(),
                        value = text,
                        maxLines = 2,
                        onValueChange = { text = it },
                        label = { Text(stringResource(id = R.string.add_todo)) })
                    Spacer(modifier = Modifier.height(spacerHeight))
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(buttonHeight)
                        .align(Alignment.CenterHorizontally), onClick = {
                        createTodoViewModel.addTodoItem(text)
                        onNavigate()
                    }) {
                        Text(text = stringResource(id = R.string.add_todo))
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