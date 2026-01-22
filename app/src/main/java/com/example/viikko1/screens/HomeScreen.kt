package com.example.viikko1.screens

import androidx.compose.runtime.*
import com.example.viikko1.viewmodel.TaskViewModel
import com.example.viikko1.domain.Task


@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {
    var newTaskTitle by remember { mutableStateOf("") }



}