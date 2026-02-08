package com.example.viikko1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.viikko1.ui.theme.Viikko1Theme
import com.example.viikko1.screens.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viikko1.navigation.*
import com.example.viikko1.viewmodel.TaskViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel: TaskViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = ROUTE_HOME
            ) {
                composable(ROUTE_HOME) {
                    HomeScreen(
                        viewModel = viewModel,
                        onTaskClick = { id ->
                            viewModel.openTask(id)
                        },
                        onAddClick = {
                            viewModel.addTaskDialogVisible.value = true
                        },
                        onNavigateCalendar = {
                            navController.navigate(ROUTE_CALENDAR)
                        },
                        onNavigateSettings = {
                            navController.navigate(ROUTE_SETTINGS)
                        }
                    )
                }
                composable(ROUTE_CALENDAR) {
                    CalendarScreen(
                        viewModel = viewModel,
                        onTaskClick = { id ->
                            viewModel.openTask(id)
                        },
                        onNavigateHome = {
                            navController.navigate(ROUTE_HOME)
                        }
                    )
                }
                composable(ROUTE_SETTINGS) {
                    SettingsScreen(
                        onNavigateHome = {
                            navController.navigate(ROUTE_HOME)
                        }
                    )
                }
            }
        }
    }
}
