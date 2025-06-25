package com.example.pesanlapang_android_app.module.detail.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pesanlapang_android_app.R
import com.example.pesanlapang_android_app.core.route.AppRouteName
import com.example.pesanlapang_android_app.core.theme.Blue
import com.example.pesanlapang_android_app.core.theme.Glass
import com.example.pesanlapang_android_app.core.theme.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import com.example.pesanlapang_android_app.module.home.model.SportModel

@Composable
fun DetailScreen(
    navController: NavHostController,
    sport: SportModel,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Blue
                ),
                shape = RoundedCornerShape(24.dp),
                onClick = {
                    navController.navigate(AppRouteName.CourtSelector)
                },
            ) {
                Text(text = "Book Now", color = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Venue Detail",
                    style = MaterialTheme.typography.h6
                )
            }

            Image(
                painter = painterResource(id = sport.assetImage),
                contentDescription = "Sport Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = sport.title,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SportInfo(R.drawable.sport, "Type", sport.type)
                    SportInfo(R.drawable.price, "Price", sport.price)
                    SportInfo(R.drawable.rating, "Rating", sport.rating)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("Description", style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.height(4.dp))
                Text(sport.description, style = MaterialTheme.typography.body2)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Location", style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.height(4.dp))
                Text(sport.location, style = MaterialTheme.typography.body2)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Open Hours", style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.height(4.dp))
                Text(sport.openHours, style = MaterialTheme.typography.body2)

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun SportInfo(
    @DrawableRes painterResourceId: Int,
    title: String,
    value: String,
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .border(1.dp, Gray, RoundedCornerShape(12.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = painterResourceId),
            contentDescription = title,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified // biar icon asli warnanya sesuai asset
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.caption
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
        )
    }
}
