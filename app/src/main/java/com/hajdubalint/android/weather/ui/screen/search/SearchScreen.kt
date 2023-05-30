package com.hajdubalint.android.weather.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hajdubalint.android.weather.ui.theme.Orange

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    var showAddDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                title = {
                    Text("Your saved cities.")
                },
                actions = {
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(
                                onClick = viewModel.onBackClicked()
                            ),
                        text = "Back"
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddDialog = true
                },
                modifier = Modifier
                    .navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add city",
                    tint = Orange
                )
            }
        }
    ) {
        val cityList = viewModel.cityList.collectAsState()
        cityList.value?.let { safeCityList ->
            LazyColumn(
                modifier = Modifier
                    .padding(it)
            ) {
                items(safeCityList) { city ->
                    CityRowItem(
                        city = city,
                        onRemoveItemClicked = viewModel.deleteCity(),
                        onItemClicked = viewModel.onCityClicked()
                    )
                }
            }
        }
        if (showAddDialog) {
            AddNewCityForm(
                addNewCity = viewModel.insertCity(),
                dialogDismiss = {
                    showAddDialog = false
                }
            )
        }
    }
}