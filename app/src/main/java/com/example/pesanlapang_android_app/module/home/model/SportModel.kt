package com.example.pesanlapang_android_app.module.home.model

import com.example.pesanlapang_android_app.R

data class SportModel(
    val id:String,
    val title: String,
    val assetImage: Int,
    val type: String,
    val rating: String,
    val description: String,
    val location: String,
    val openHours:String,
    val price: String,
)


val popularNearYouSport = listOf(
    SportModel(
        id= "1",
        title = "Zupper Tennis",
        assetImage = R.drawable.tennis,
        type = "Tennis",
        rating = "7.7/10",
        description = "A modern indoor tennis court with cushioned flooring and bright lighting, perfect for training or friendly matches regardless of weather.",
        location = "Jl. Apel No. 11, Surabaya, East Java",
        openHours = "06:00 – 21:00",
        price = "150,000"
    ),
    SportModel(
        id= "2",
        title = "Zapper Basketball",
        assetImage = R.drawable.basketball,
        type = "Basketball",
        rating = "7.0/10",
        description = "Spacious outdoor basketball court with smooth asphalt flooring and floodlights for night games. Ideal for casual games and weekend matches.",
        location = "Jl. Jeruk No.11, Surabaya, East Java",
        openHours = "08:00 – 22:00",
        price = "100,000"
    ),
    SportModel(
        id= "3",
        title = "Zipper Mini Soccer",
        assetImage = R.drawable.minisoccer,
        type = "Soccer",
        rating = "8.0/10",
        description = "A semi-covered mini soccer field with synthetic turf, suitable for 5v5 and 7v7 matches. Equipped with lighting for evening games and comfortable team areas.",
        location = "Jl. Semangka No.11, Surabaya, East Java\"",
        openHours = "07:00 – 23:00",
        price = "700,000"
    )
)