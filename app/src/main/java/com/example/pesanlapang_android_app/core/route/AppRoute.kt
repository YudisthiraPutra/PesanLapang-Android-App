package com.example.pesanlapang_android_app.core.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pesanlapang_android_app.module.detail.presentation.DetailScreen
import com.example.pesanlapang_android_app.module.home.model.popularNearYouSport
import com.example.pesanlapang_android_app.module.home.presentation.BottomNavBar
import com.example.pesanlapang_android_app.module.home.presentation.HomeScreen
import com.example.pesanlapang_android_app.module.login.presentation.LoginScreen
import com.example.pesanlapang_android_app.module.signup.presentation.SignUpScreen
import com.example.pesanlapang_android_app.module.profile.presentation.ProfileScreen
import com.example.pesanlapang_android_app.module.profile.presentation.MyBookingScreen
import com.example.pesanlapang_android_app.module.seat_selector.presentation.CourtSelectorScreen
import com.example.pesanlapang_android_app.module.profile.presentation.Booking

import com.example.pesanlapang_android_app.module.orderdetail.presentation.OrderDetailScreen
object AppRoute {
    @Composable
    fun GenerateRoute(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "login",
        ) {
            composable(AppRouteName.Home) {
                HomeScreen(navController = navController)
            }
            composable("${AppRouteName.Detail}/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                val sport = popularNearYouSport.first { it.id == id }
                DetailScreen(navController = navController, sport)
            }
            composable(AppRouteName.CourtSelector) {
                CourtSelectorScreen(navController = navController)
            }
            composable(AppRouteName.Profile) {
                ProfileScreen(navController = navController)
            }
            composable(
                route = "${AppRouteName.OrderDetail}/{courts}/{date}/{time}"
            ) { backStackEntry ->
                val courts = backStackEntry.arguments?.getString("courts")?.split(",") ?: listOf()
                val date = backStackEntry.arguments?.getString("date") ?: ""
                val time = backStackEntry.arguments?.getString("time") ?: ""

                OrderDetailScreen(navController = navController, courts = courts, date = date, time = time)
            }
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignUpScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable(AppRouteName.MyBookings) {
                // Contoh data dummy
                val bookings = listOf(
                    Booking("1", "Court A1", "2025-07-01", "10:00", 150),
                    Booking("2", "Court B2", "2025-07-05", "14:00", 150)
                )
                MyBookingScreen(navController = navController, bookings = bookings)
            }
        }
    }
}