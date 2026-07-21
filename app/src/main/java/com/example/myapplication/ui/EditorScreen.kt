package com.example.myapplication.ui

import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.viewmodel.ScheduleViewModel
import java.time.Instant
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    eventId: Long,
    navController: NavController,
    viewModel: ScheduleViewModel
) {
    val existingEvent by remember(eventId) { viewModel.observeEvent(eventId) }
        .collectAsState(initial = null)
    var title by remember(existingEvent) { mutableStateOf(existingEvent?.title ?: "") }
    var date by remember(existingEvent) { mutableStateOf(existingEvent?.date ?: "") }
    var time by remember(existingEvent) { mutableStateOf(existingEvent?.time ?: "") }
    var description by remember(existingEvent) { mutableStateOf(existingEvent?.description ?: "") }
    var colorIndex by remember(existingEvent) { mutableStateOf(existingEvent?.colorIndex ?: 0) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название события") })

        OutlinedButton(onClick = {showDatePicker = true}) {
            Text(if (date.isEmpty()) "Выбрать дату" else date)
        }
        OutlinedButton(onClick = {showTimePicker = true}) {
            Text(if (time.isEmpty()) "Выбрать время" else time)
        }

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") })

        Button(onClick = {
            if (existingEvent != null) {
                viewModel.updateEvent(eventId, title, date, time, description, colorIndex)
            } else {
                viewModel.addEvent(title, date, time, description, colorIndex)
            }
            navController.popBackStack()
        }) {
            Text(if (existingEvent != null) "Сохранить изменения" else "Добавить событие")
        }

        if (existingEvent != null) {
            TextButton(onClick = {showDeleteConfirm = true}) {
                Text("Удалить событие", color = MaterialTheme.colorScheme.error)
            }
        }
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = {showDatePicker = false},
            confirmButton = {
                TextButton(onClick ={
                    datePickerState.selectedDateMillis?.let {millis ->
                        date = Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate().toString()
                    }
                    showDatePicker = false
                }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = {showDatePicker = false}
                ) {
                    Text("Отмена")
                }
            }
        ) { DatePicker(state = datePickerState) }
    }

    if (showTimePicker) {
        val timePickerState = rememberTimePickerState(is24Hour = true)
        AlertDialog(
            onDismissRequest = {showTimePicker = false},
            text = { TimePicker(state = timePickerState) },
            confirmButton = {
                TextButton(onClick = {
                    time = "%02d:%02d".format(timePickerState.hour, timePickerState.minute)
                    showTimePicker = false
                }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {showTimePicker = false}
                ) {
                    Text("Отмена")
                }
            }
        )

    }

    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = { Text("Удалить событие?") },
            text = { Text("Это действие нельзя отменить.") },
            confirmButton = {
                TextButton(onClick = {
                    existingEvent?.let { viewModel.removeEvent(it) }
                    showDeleteConfirm = false
                    navController.popBackStack()
                }) { Text("Удалить") }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = false }) { Text("Отмена") }
            }
        )
    }

}