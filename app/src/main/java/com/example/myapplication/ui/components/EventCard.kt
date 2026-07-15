package com.example.myapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun EventCard(title: String, time: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
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