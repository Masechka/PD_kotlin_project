package com.example.myapplication.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputWidgetsDemo() {
    var isChecked by remember { mutableStateOf(false) }
    var isSwitchOn by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(50f) }
    var selectedOption by remember { mutableStateOf("Утро") }
    var textCheck by remember { mutableStateOf("Сделать домашку") }
    var textSwitch by remember { mutableStateOf("off") }


    Column(modifier = Modifier.padding(32.dp)) {
        Row{
            Checkbox(isChecked, onCheckedChange = {
                isChecked = it
                if (isChecked) {
                    textCheck = "Домашка сделана"
                } else { textCheck = "Сделать домашку"}
            })
            Text(textCheck)
        }

        Row{
            Switch(isSwitchOn, {
                isSwitchOn = it
                if (isSwitchOn) {
                    textSwitch = "on"
                } else { textSwitch = "off"}
            })
            Text(textSwitch)
        }

        listOf("Утро", "День", "Вечер").forEach { option ->
            Row{
                RadioButton(
                    selected = (selectedOption == option),
                    onClick = {selectedOption = option}
                )
                Text(option)
            }
        }

        Text("Громкость напоминания: ${sliderValue.toInt()}")
        Slider(value = sliderValue, onValueChange = {sliderValue = it}, valueRange = 0f..100f)
    }
}

@Preview(showBackground = true)
@Composable
fun InputWidgetsDemoPreview() {
    InputWidgetsDemo()
}