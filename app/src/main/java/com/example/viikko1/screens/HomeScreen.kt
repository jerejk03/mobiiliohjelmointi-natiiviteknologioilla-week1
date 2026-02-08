package com.example.viikko1.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.example.viikko1.ui.theme.Viikko1Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.viewmodel.TaskViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.material.icons.filled.CalendarMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = viewModel(),
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {},
    onNavigateSettings: () -> Unit = {}
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val addTaskFlag by viewModel.addTaskDialogVisible.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(
            title = { Text("Task List") },
            actions = {
                IconButton(onClick = onNavigateCalendar) {
                     Icon(Icons.Default.CalendarMonth, contentDescription = "Calendar")
                 }
                IconButton(onClick = onNavigateSettings) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                }
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onAddClick
            ) {
                Text("+")
                }

            Button(
                onClick = {
                    viewModel.sortByDueDate()
                }
            ) {
                Text("Sort by due date")
            }

            Button(
                onClick = {
                    viewModel.filterByDone(true)
                }
            ) {
                Text("Show done tasks")
            }

        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                Card(modifier = Modifier
                    .padding(8.dp)
                    .clickable { onTaskClick(task.id) }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Checkbox(
                            checked = task.done,
                            onCheckedChange = {
                                viewModel.toggleDone(task.id)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "${task.id}. ${task.title} - ${task.description} " +
                                    "- priority: ${task.priority} - ${task.dueDate}",
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = {
                                viewModel.removeTask(task.id)
                            }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
    if (selectedTask != null) {
        DetailDialog(
            task = selectedTask!!,
            onClose = { viewModel.closeDialog() },
            onUpdate = { viewModel.updateTask(it) }
        )
    }
    if (addTaskFlag) {
        AddDialog(
            onClose = { viewModel.addTaskDialogVisible.value = false },
            onUpdate = { viewModel.addTask(it) }
        )
    }
}