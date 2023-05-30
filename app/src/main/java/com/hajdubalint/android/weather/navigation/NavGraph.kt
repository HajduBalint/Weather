package com.hajdubalint.android.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hajdubalint.android.weather.ui.screen.home.HomeScreen
import com.hajdubalint.android.weather.ui.screen.search.SearchScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Navigation.HOME.route + "/{cityName}"
    ) {
        composable(
            route = Navigation.HOME.route + "/{cityName}",
            arguments = listOf(navArgument("cityName") {
                type = NavType.StringType
                nullable = true
            })
        ) {
            HomeScreen(it.arguments?.getString("cityName"))
        }
        composable(
            route = Navigation.SEARCH.route
        ) {
            SearchScreen()
        }
        composable(
            route = Navigation.HOME.route
        ) {
            HomeScreen(null)
        }
    }
}