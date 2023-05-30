package com.hajdubalint.android.weather.navigation

import androidx.navigation.NavHostController

class NavigationUtil {

    private var navHostController: NavHostController? = null

    fun setController(controller: NavHostController) {
        navHostController = controller
    }

    fun getController() = navHostController!!

    fun clear() {
        navHostController = null
    }

    fun navigateTo(route: String) {
        navHostController?.navigate(route)
    }
}