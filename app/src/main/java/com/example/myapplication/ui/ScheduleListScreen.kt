package com.example.myapplication.ui

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.model.Event
import com.example.myapplication.ui.components.EventCard
import com.example.myapplication.viewmodel.ScheduleViewModel


@Composable
fun ScheduleListScreen(
    navController: NavController,
    viewModel: ScheduleViewModel
) {
    val events by viewModel.events.collectAsState()
    var query by remember { mutableStateOf("") }
    var sortByTitle by remember { mutableStateOf(false) }

    val filteredEvents = events
        .filter { it.title.contains(query, ignoreCase = true) }
    val sortedEvents = if (sortByTitle) {
        filteredEvents.sortedBy { it.title }
    } else {
        filteredEvents.sortedBy { "${it.date} ${it.time}" }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("editor?eventId=-1")}) {
                Icon(Icons.Default.Add, contentDescription = "Добавить событие")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedTextField(
                value = query,
                onValueChange = {query = it},
                label = {Text("Поиск по названию")},
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("По времени")
                Spacer(modifier = Modifier.width(12.dp))
                Switch(checked = sortByTitle, onCheckedChange = {sortByTitle = it})
                Spacer(modifier = Modifier.width(12.dp))
                Text("По названию")
            }

            LazyColumn {
                items(sortedEvents) { event ->
                    EventCard(
                        title = event.title,
                        date = event.date,
                        time = event.time,
                        modifier = Modifier.clickable{
                            navController.navigate("editor?eventId=${event.id}")
                        }
                    )

                }
            }
        }
    }
}

