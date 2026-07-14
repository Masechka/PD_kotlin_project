package com.example.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.Event
import com.example.myapplication.ui.components.EventCard
import com.example.myapplication.viewmodel.ScheduleViewModel

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val events by viewModel.events.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = {Text("Название события")})
        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = {Text("Время")})
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = {Text("Описание")})

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.addEvent(Event(title, time, description))
                title = ""
                time = ""
                description = ""
            }
        ) {
            Text("Добавить событие")
        }

        Spacer(modifier = Modifier.height(16.dp))

//        LazyColumn(modifier = Modifier.weight(1f)) {
//            items(items = events) { event ->
//                    EventCard(
//                        title = event.title,
//                        time = event.time,
//                        onClick = {viewModel.removeEvent(event)}
//                    )
//            }
//        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(events) { event ->
                EventCard(
                    title = event.title,
                    time = event.time,
                    onClick = {viewModel.removeEvent(event)}
                )
            }
        }
    }
}

@Preview
@Composable
fun ScheduleScreenPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ScheduleScreen(modifier = Modifier.padding(innerPadding))
    }
}