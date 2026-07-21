package com.example.myapplication.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.EventCard
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun MultiButtonDemo() {
    var firstText by remember { mutableStateOf("Еще не нажато") }
    var secondText by remember { mutableStateOf("Еще не нажато") }

    Row(modifier = Modifier.padding(12.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = firstText)
            Button(onClick = { firstText = "Нажата первая кнопка"}) {
                Text(text = "Кнопка 1")
            }
        }
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = secondText)
            Button(onClick = { secondText = "Нажата вторая кнопка"}) {
                Text(text = "Кнопка 2")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiButtonDemoPreview() {
    MultiButtonDemo()
}


@Composable
fun FakeNavigationDemo() {
    var screen by remember {  mutableStateOf("list")}

    when (screen) {
        "list" -> Column(modifier = Modifier.padding(52.dp)) {
            EventCard("ПД", "", "11:00-14:00", 2)
            Button(onClick = {screen = "details"}) {
                Text(text = "Поробнее")
            }
        }
        "details" -> Column(modifier = Modifier.padding(52.dp)) {
            Text("Здесь будет более подробная информация о событии")
            Button(onClick = { screen = "list" }) {
                Text(text = "Назад")
            }
        }
    }
}

@Preview
@Composable
fun FakeNavigationDemoPreview() {
    FakeNavigationDemo()
}

