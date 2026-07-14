package com.example.myapplication

import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.ScheduleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScheduleScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EventCard(title: String, time: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = time, style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EventCardPreview() {
    MyApplicationTheme {
        Column {
            EventCard("ПД", "11:00-14:00")
            EventCard("Интенсив", "15:00-17:00")
        }
    }
}

data class Event(val title: String, val time: String, val description: String)

@Composable
fun EventListDemo() {
    val events = remember {
        mutableStateListOf(
            Event("ПД", "11:00 - 14:00", "онлайн"),
            Event("Встреча", "14:00 - 15:00", "демострация работы"),
            Event("интенсив", "15:00 - 17:00", "онлайн")
        )
    }

    LazyColumn {
        items(events) {
            event -> EventCard(title = event.title, time = event.time)
        }
    }
}

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val events by viewModel.events.collectAsState()

//    val events = remember {
//        mutableStateListOf(
//            Event("ПД", "11:00 - 14:00", "онлайн"),
//            Event("Встреча", "14:00 - 15:00", "демострация работы"),
//            Event("интенсив", "15:00 - 17:00", "онлайн")
//        )
//    }

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

        LazyColumn {
            items(events) {
                    event -> EventCard(title = event.title, time = event.time)
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