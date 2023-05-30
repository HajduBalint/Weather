package com.hajdubalint.android.weather.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hajdubalint.android.weather.ui.theme.Orange
import com.hajdubalint.android.weather.utils.model.City

@Composable
fun CityRowItem(
    city: City,
    onRemoveItemClicked: (City) -> Unit,
    onItemClicked: (String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable(
                onClick = { onItemClicked(city.name) }
            )
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = city.name
            )
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .clickable(
                        onClick = { onRemoveItemClicked(city) }
                    ),
                tint = Orange
            )
        }
    }
}