package com.example.pesanlapang_android_app.module.home.presentation
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pesanlapang_android_app.core.route.AppRouteName

@Composable
fun BottomNavBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == AppRouteName.Home,
            onClick = {
                if (currentRoute != AppRouteName.Home) {
                    navController.navigate(AppRouteName.Home) {
                        launchSingleTop = true
                        popUpTo(AppRouteName.Home)
                    }
                }
            }
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentRoute == AppRouteName.Profile,
            onClick = {
                if (currentRoute != AppRouteName.Profile) {
                    navController.navigate(AppRouteName.Profile) {
                        launchSingleTop = true
                        popUpTo(AppRouteName.Home)
                    }
                }
            }
        )
    }
}