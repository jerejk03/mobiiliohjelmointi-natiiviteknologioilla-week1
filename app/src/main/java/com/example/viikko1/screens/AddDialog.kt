package com.example.viikko1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.viewmodel.TaskViewModel

@Composable
fun AddDialog(viewModel: TaskViewModel = viewModel(), onClose: () -> Unit, onUpdate: (Task) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableIntStateOf(1) }
    var date by remember { mutableStateOf("") }
    var task by remember {
        mutableStateOf(Task(id = 0, title = "", description = "", priority = 0, dueDate = "", done = false))}

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Add task") },
        text = {
            Column {
                TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                TextField(value = date, onValueChange = { date = it }, label = { Text("Date (YYYY-MM-DD)") })

                //Row(verticalAlignment = Alignment.CenterVertically) {

                    Button(
                        onClick = {
                            if (priority < 3) {
                                priority += 1
                            } else {
                                priority = 1
                            }
                        }
                    ) {
                        val buttonText = when (priority) {
                            1 -> "Priority : low"
                            2 -> "Priority: medium"
                            else -> "Priority: high"
                        }
                        Text(buttonText)
                    }
                }
           // }
        },
        confirmButton = {
            Button(onClick = {
                task = Task(id = 0, title = title, description = description, priority = priority, dueDate = date, done = false)
                viewModel.addTask(task)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onClose) {
                Text("Cancel")
            }
        }
    )
}