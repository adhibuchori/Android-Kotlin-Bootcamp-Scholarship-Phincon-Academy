package com.adhibuchori.narumi.domain.trip

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TripModel(
    val uuid: String,
    val tripImage: Int,
    val tripName: String,
    val tripLocation: String,
    val tripCost: Int,
    val tripDescription: String,
    val tripDuration: String,
    val tripDate: String,
    val tripOrderDate: String,
    val tripCategory: String,
) : Parcelable