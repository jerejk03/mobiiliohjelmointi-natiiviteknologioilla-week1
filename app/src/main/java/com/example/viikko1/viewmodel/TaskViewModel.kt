package com.example.viikko1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    val addTaskDialogVisible = MutableStateFlow<Boolean>(false)


    init {
        _tasks.value = mockTasks
    }

    fun addTask(task: Task) {
        _tasks.value += task
        addTaskDialogVisible.value = false
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map { if (it.id == id) it.copy(done = !it.done)
        else it
        }
    }

    fun filterByDone(done: Boolean) {
        _tasks.value = _tasks.value.filter { it.done == done }
    }

    fun sortByDueDate() {
        _tasks.value = _tasks.value.sortedBy { it.dueDate }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }

    fun closeDialog() {
        _selectedTask.value = null
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task

    }

    fun updateTask(updated: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updated.id) {
                updated
            } else {
                it
            }
        }
        _selectedTask.value = null
    }

    fun openTask(id: Int) {
        val task = _tasks.value.find { it.id == id }
        _selectedTask.value = task
    }
}

