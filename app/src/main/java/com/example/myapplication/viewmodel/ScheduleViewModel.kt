package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.EventRepository
import com.example.myapplication.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.sql.Date

class ScheduleViewModel(private val repository: EventRepository) : ViewModel() {
    val events: StateFlow<List<Event>> = repository.observeAll().
    stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun observeEvent(id: Long): Flow<Event?> = repository.observeById(id)
    fun addEvent(title: String, date: String, time: String, description: String, colorIndex: Int) {
        viewModelScope.launch {
            repository.add(Event(title = title, date = date, time = time, description = description, colorIndex = colorIndex))
        }
    }

    fun updateEvent(id: Long, title: String, date: String, time: String, description: String, colorIndex: Int) {
        viewModelScope.launch {
            repository.update(Event(id = id, title = title, date = date, time = time, description = description, colorIndex = colorIndex))
        }
    }

    fun removeEvent(event: Event) {
        viewModelScope.launch {
            repository.delete(event)
        }
    }

}