package com.example.myapplication.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyFirstScreen() {
    Column(modifier = Modifier.padding(32.dp)) {
        Text(text = "Это мое первое приложение")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* пока ничего */ }) {
            Text(text = "Нажми меня")
        }
        Button(onClick = {}) {
            Text(text = "2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyFirstScreenPreview() {
    MyFirstScreen()
}