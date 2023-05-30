package com.hajdubalint.android.weather.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun AddNewCityForm(
    addNewCity: (String) -> Unit,
    dialogDismiss: () -> Unit
) {
    Dialog(onDismissRequest = dialogDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var cityName by remember {
                    mutableStateOf("")
                }

                OutlinedTextField(
                    value = cityName,
                    label = {
                        Text(
                            text = "City name"
                        )
                    },
                    onValueChange = {
                        cityName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(onClick = {
                    addNewCity(cityName)
                    dialogDismiss()
                }) {
                    Text(text = "Add City")
                }
            }
        }
    }
}