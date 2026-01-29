package com.example.viikko1.screens

import android.app.AlertDialog
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
import com.example.viikko1.domain.Task


@Composable
fun DetailDialog(task: Task, onClose: () -> Unit, onUpdate: (Task) -> Unit) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var priority by remember { mutableIntStateOf(task.priority) }
    var dueDate by remember { mutableStateOf(task.dueDate) }

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Edit Task") },
        text = {
            Column {
                TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                TextField(value = priority.toString(), onValueChange = { priority = it.toIntOrNull() ?: 1 }, label = { Text("Priority") })
                TextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text("Due Date") })
            }
        },
        confirmButton = {
            Button(onClick = {
                onUpdate(task.copy(title = title, description = description, priority = priority, dueDate = dueDate))
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