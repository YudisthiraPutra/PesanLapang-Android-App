package com.example.pesanlapang_android_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.pesanlapang_android_app.core.route.AppRoute
import com.example.pesanlapang_android_app.core.theme.PesanLapangAndroidAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /// from Accompanist, setting a status bar
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = true,
            )
            systemUiController.setNavigationBarColor(
                color = Color.Transparent,
                darkIcons = true,
            )

            PesanLapangAndroidAppTheme {
                /// main navigation
                val navController = rememberNavController()
                AppRoute.GenerateRoute(navController = navController)
            }
        }
    }
}