package com.example.myapplication.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.EditorScreen
import com.example.myapplication.ui.ScheduleListScreen
import com.example.myapplication.viewmodel.ScheduleViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    // val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val viewModel: ScheduleViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") { ScheduleListScreen(navController, viewModel) }
        composable(
            "editor?eventId={eventId}",
            arguments = listOf(
                navArgument("eventId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            backStackEntry ->
            val eventId = backStackEntry.arguments?.getLong("eventId") ?: -1L
            EditorScreen(eventId = eventId, navController = navController, viewModel = viewModel)
        }
    }
}