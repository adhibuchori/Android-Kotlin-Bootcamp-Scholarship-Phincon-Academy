package com.adhibuchori.narumi.data.trip.model

import java.util.UUID

data class TripResponse(
    val uuid: String = UUID.randomUUID().toString(),
    val tripImage: Int,
    val tripName: String,
    val tripLocation: String,
    val tripDate: String,
    val tripOrderDate: String,
    val tripDuration: String,
    val tripCost: Int,
    val tripDescription: String,
    val tripCategory: String
)