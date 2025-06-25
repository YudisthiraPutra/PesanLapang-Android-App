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
                    .wrapContentWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Glass
                ),
                shape = RoundedCornerShape(32.dp),
                onClick = {
                    navController.navigate(AppRouteName.CourtSelector)
                },
            ) {
                Text(text = "Book Now", color = Blue)
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
                modifier = Modifier.padding(
                    horizontal = 16.dp, vertical = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back Button")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Venue Detail", style = MaterialTheme.typography.h6)
            }
            Row(
                modifier = Modifier
                    .height(320.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = sport.assetImage),
                    contentDescription = "Sport Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .weight(0.7f)
                        .height(320.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.width(24.dp))
                Column(
                    modifier = Modifier
                        .height(320.dp)
                        .weight(0.3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    SportInfo(
                        painterResourceId = R.drawable.sport,
                        title = "Sport",
                        value = sport.type
                    )
                    SportInfo(
                        painterResourceId = R.drawable.price,
                        title = "Price",
                        value = sport.price
                    )
                    SportInfo(
                        painterResourceId = R.drawable.rating,
                        title = "Rating",
                        value = sport.rating
                    )
                }
            }
            Text(
                sport.title, style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(
                    horizontal = 24.dp, vertical = 16.dp
                )
            )
            Text(
                "Description", style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(
                    horizontal = 24.dp
                )
            )
            Text(
                sport.description, style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    horizontal = 24.dp, vertical = 16.dp
                )
            )
            Text(
                "Location", style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(
                    horizontal = 24.dp
                )
            )
            Text(
                sport.location, style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    horizontal = 24.dp, vertical = 16.dp
                )
            )
            Text(
                "Open Hours", style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(
                    horizontal = 24.dp
                )
            )
            Text(
                sport.openHours, style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(
                    horizontal = 24.dp, vertical = 16.dp
                )
            )
            Spacer(modifier = Modifier.height(64.dp))
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
            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { }
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = painterResourceId),
            contentDescription = title,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = title, style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.subtitle1)
    }
}