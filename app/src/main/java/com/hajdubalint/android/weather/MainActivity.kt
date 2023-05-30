package com.hajdubalint.android.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.hajdubalint.android.weather.navigation.NavGraph
import com.hajdubalint.android.weather.navigation.NavigationUtil
import com.hajdubalint.android.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationUtil: NavigationUtil

    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analytics = Firebase.analytics
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