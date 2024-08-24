package com.example.mztodo.ui.screens.create_todo

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreateTodoScreen(onNavigate: () -> Unit) {
    var text by remember { mutableStateOf("") }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        val buttonHeight = maxHeight * 0.064f
        val spacerHeight = maxHeight * 0.04f

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = { Text("Add TODO") })
            Spacer(modifier = Modifier.height(spacerHeight))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight)
                    .align(Alignment.CenterHorizontally),
                onClick = {
//            viewModel.addTodoItem(text)
                    onNavigate()
                }) {
                Text(text = "Add todo")
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