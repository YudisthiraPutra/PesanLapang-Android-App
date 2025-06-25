package com.example.pesanlapang_android_app.module.orderdetail.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import com.example.pesanlapang_android_app.core.theme.Blue
import com.example.pesanlapang_android_app.core.theme.Glass
import com.example.pesanlapang_android_app.core.route.AppRouteName
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
@Composable
fun OrderDetailScreen(
    navController: NavController,
    courts: List<String>,
    date: String,
    time: String
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Order Detail",
                        style = MaterialTheme.typography.h6
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 4.dp,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Selected Courts", style = MaterialTheme.typography.subtitle2)
                        Text(
                            courts.joinToString(", "),
                            style = MaterialTheme.typography.body1
                        )

                        Text("Date", style = MaterialTheme.typography.subtitle2)
                        Text(date, style = MaterialTheme.typography.body1)

                        Text("Time", style = MaterialTheme.typography.subtitle2)
                        Text(time, style = MaterialTheme.typography.body1)

                        Text("Total Price", style = MaterialTheme.typography.subtitle2)
                        Text(
                            "Rp ${courts.size * 150},000",
                            style = MaterialTheme.typography.h6,
                            color = Blue
                        )
                    }
                }
            }

            var showDialog by remember { mutableStateOf(false) }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Payment Completed") },
                    text = { Text("Your booking has been confirmed successfully.") },
                    confirmButton = {
                        TextButton(onClick = {
                            showDialog = false
                            navController.popBackStack(AppRouteName.Home, inclusive = false)
                        }) {
                            Text("OK")
                        }
                    }
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Blue
                ),
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    showDialog = true
                }
            ) {
                Text("Confirm & Pay", color = Color.White)
            }

        }
    }
}