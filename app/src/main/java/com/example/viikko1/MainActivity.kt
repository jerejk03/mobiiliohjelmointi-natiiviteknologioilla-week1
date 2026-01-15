package com.example.viikko1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.addTask
import com.example.viikko1.domain.mockTasks
import com.example.viikko1.domain.toggleDone
import com.example.viikko1.ui.theme.Viikko1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskList(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableIntStateOf(1) }
    var date by remember { mutableStateOf("") }
    var done by remember { mutableStateOf(false) }
    var taskList by remember { mutableStateOf( mockTasks )}

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("Task list", fontSize = 24.sp)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    taskList = taskList.sortedBy { it.dueDate }
                }
            ) {
                Text("Sort by due date")
            }

            Button(
                    onClick = {
                        taskList = taskList.filter { it.done }
                    }
                    ) {
                Text("Show done tasks")
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(taskList) { task ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = {
                            taskList = toggleDone(taskList, task.id)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "${task.id}. ${task.title} - ${task.description} " +
                                "- priority: ${task.priority} - ${task.dueDate}", fontSize = 14.sp
                    )
                }
            }
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Task name") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2
        )

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            Button(
                onClick = {
                    if (selectedPriority < 3) {
                        selectedPriority += 1
                    } else {
                        selectedPriority = 1
                    }
                }
            ) {
                val buttonText = when(selectedPriority) {
                    1 -> "Priority : low"
                    2 -> "Priority: medium"
                    else -> "Priority: high"
                }
                Text(buttonText)
            }
        }

        Button(
            onClick = {
                val newTask = Task(
                    id = taskList.size + 1,
                    title = name,
                    description = description,
                    priority = selectedPriority,
                    dueDate = date,
                    done = done
                )
                taskList = addTask(taskList, newTask)

            },
            content = {
                Text("Add task")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    Viikko1Theme {
        TaskList()
    }
}
