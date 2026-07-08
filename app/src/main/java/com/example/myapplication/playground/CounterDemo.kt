package com.example.myapplication.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CounterDemo() {
    var counter by remember { mutableIntStateOf(value = 0) }
    Column(modifier = Modifier.padding(all = 16.dp)) {
        Text(text = " Счётчик $counter")
        Button(onClick = {counter++}){
            Text(text = "Прибавить")
        }
        Button(onClick = {counter--}) {
            Text(text = "Прибавить")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterDemoPreview() {
    CounterDemo()
}

