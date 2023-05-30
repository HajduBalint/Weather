package com.hajdubalint.android.weather.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hajdubalint.android.weather.R


@Composable
fun ErrorScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    errorMessage: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(96.dp),
            painter = painterResource(
                id = R.drawable.ic_error
            ),
            contentDescription = "Error icon"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            text = errorMessage,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        val interactionSource = remember { MutableInteractionSource() }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = viewModel.onTryAgainCLicked(),
                ),
            text = "Click here to try again.",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
    }
}