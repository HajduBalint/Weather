package com.hajdubalint.android.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.hajdubalint.android.weather.navigation.NavGraph
import com.hajdubalint.android.weather.navigation.NavigationUtil
import com.hajdubalint.android.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationUtil: NavigationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navigationUtil.setController(rememberNavController())
            WeatherTheme {
                NavGraph(
                    navHostController = navigationUtil.getController()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationUtil.clear()
    }
}