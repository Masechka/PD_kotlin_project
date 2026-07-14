package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScheduleViewModel : ViewModel() {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    fun addEvent(event: Event) {
        _events.value = events.value + event
    }

    fun removeEvent(event: Event) {
        _events.value = events.value - event
    }
}