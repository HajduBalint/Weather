package com.hajdubalint.android.weather.ui.custom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hajdubalint.android.weather.utils.model.WeatherInfoItem

@Composable
fun WeatherInfoItem(
    weatherInfoItem: WeatherInfoItem
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weatherInfoItem.title,
                fontSize = 20.sp
            )
            Text(
                text = weatherInfoItem.value,
                fontSize = 20.sp
            )

        }
    }
}