package com.example.pesanlapang_android_app.module.seat_selector.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pesanlapang_android_app.core.theme.Black
import com.example.pesanlapang_android_app.core.theme.Blue
import com.example.pesanlapang_android_app.core.theme.Glass
import com.example.pesanlapang_android_app.core.theme.Gray
import com.example.pesanlapang_android_app.core.theme.LightGray
import com.example.pesanlapang_android_app.core.route.AppRouteName
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun CourtSelectorScreen(
    navController: NavController,
) {
    val today = LocalDate.now()
    val dateScrollState = rememberScrollState()
    val timeScrollState = rememberScrollState()

    val selectedCourt = remember {
        mutableStateListOf<String>()
    }

    val selectedDate = remember {
        mutableStateOf<LocalDate?>(null)
    }

    val selectedTime = remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        backgroundColor = LightGray
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
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
                Text(text = "Select Court", style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = Modifier.height(100.dp))

            /// court mapping
            for (i in 1..2) {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    for (j in 1..2) {
                        val courtNumber = "${(64 + i).toChar()}$j"
                        CourtComp(
                            isEnabled = i != 2,
                            isSelected = selectedCourt.contains(courtNumber),
                            courtNumber = courtNumber
                        ) { selected, court ->
                            if (selected) {
                                selectedCourt.remove(court)
                            } else {
                                selectedCourt.add(court)
                            }
                        }

                        if (j != 2) Spacer(modifier = Modifier.width(if (j == 1) 16.dp else 8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
            /// indicator
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {

                CourtComp(isEnabled = false)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Reserved",
                    style = MaterialTheme.typography.caption,
                )

                Spacer(modifier = Modifier.width(16.dp))

                CourtComp(isEnabled = true, isSelected = true)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Selected",
                    style = MaterialTheme.typography.caption,
                )

                Spacer(modifier = Modifier.width(16.dp))

                CourtComp(isEnabled = true, isSelected = false)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Available",
                    style = MaterialTheme.typography.caption,
                )
            }
            Spacer(modifier = Modifier.height(170.dp))
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Select Date",
                        style = MaterialTheme.typography.subtitle1,
                    )
                    Row(
                        modifier = Modifier.horizontalScroll(dateScrollState),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 0..14) {
                            val date = today.plusDays(i.toLong())
                            DateComp(
                                date = date, isSelected = selectedDate.value == date
                            ) {
                                selectedDate.value = it
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.horizontalScroll(timeScrollState),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 10..22 step 2) {
                            val time = "$i:00"
                            TimeComp(
                                time = time, isSelected = selectedTime.value == time
                            ) {
                                selectedTime.value = it
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = "Total Price",
                                style = MaterialTheme.typography.subtitle1,
                            )
                            Text(
                                text = "Rp ${selectedCourt.size * 150},000",
                                style = MaterialTheme.typography.subtitle1,
                            )
                        }
                        val isEnabled = selectedCourt.isNotEmpty() && selectedDate.value != null && selectedTime.value != null
                        Button(
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (isEnabled) Blue else Blue.copy(alpha = 0.5f),
                            ),
                            shape = RoundedCornerShape(32.dp),
                            enabled = isEnabled,
                            onClick = {
                                val courtParam = selectedCourt.joinToString(",")
                                val dateParam = selectedDate.value?.toString() ?: ""
                                val timeParam = selectedTime.value ?: ""

                                navController.navigate(
                                    "${AppRouteName.OrderDetail}/$courtParam/$dateParam/$timeParam"
                                )
                            },
                        ) {
                            Text("Continue", color = Color.White)
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun TimeComp(
    time: String,
    isSelected: Boolean = false,
    onClick: (String) -> Unit = {},
) {
    val color = when {
        isSelected -> Blue
        else -> Black.copy(alpha = 0.15f)
    }
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick(time)
            }, shape = RoundedCornerShape(16.dp), color = color
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(12.dp),
        )
    }
}


@Composable
fun DateComp(
    date: LocalDate,
    isSelected: Boolean = false,
    onClick: (LocalDate) -> Unit = {},
) {
    val color = when {
        isSelected -> Blue
        else -> Black.copy(alpha = 0.15f)
    }
    val textBg = when {
        isSelected -> Blue
        else -> Color.Transparent
    }
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick(date)
            }, shape = RoundedCornerShape(16.dp), color = color
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                style = MaterialTheme.typography.caption
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(textBg)

                    .padding(4.dp),
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}

@Composable
fun CourtComp(
    isEnabled: Boolean = false,
    isSelected: Boolean = false,
    courtNumber: String = "",
    onClick: (Boolean, String) -> Unit = { _, _ -> },
) {
    val courtColor = when {
        !isEnabled -> Color.Gray
        isSelected -> Blue
        else -> Color.White
    }

    val textColor = when {
        isSelected -> Color.White
        else -> Color.Black
    }

    Box(modifier = Modifier
        .size(64.dp)
        .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))
        .background(color = courtColor)
        .clickable {
            onClick(isSelected, courtNumber);
        }
        .padding(8.dp), contentAlignment = Alignment.Center) {
        Text(
            courtNumber,
            style = MaterialTheme.typography.caption.copy(color = textColor),
        )
    }
}