package com.example.pesanlapang_android_app.module.profile.presentation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.pesanlapang_android_app.core.theme.Blue

data class Booking(
    val id: String,
    val courtName: String,
    val date: String,
    val time: String,
    val price: Int
)

@Composable
fun MyBookingScreen(navController: NavController, bookings: List<Booking>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Bookings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = Blue,
                contentColor = MaterialTheme.colors.onPrimary
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (bookings.isEmpty()) {
                item {
                    Text("No bookings found.", style = MaterialTheme.typography.body1)
                }
            } else {
                items(bookings) { booking ->
                    BookingItem(booking)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun BookingItem(booking: Booking) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Court: ${booking.courtName}", style = MaterialTheme.typography.h6)
            Text(text = "Date: ${booking.date}")
            Text(text = "Time: ${booking.time}")
            Text(text = "Price: Rp ${booking.price},000")
        }
    }
}
