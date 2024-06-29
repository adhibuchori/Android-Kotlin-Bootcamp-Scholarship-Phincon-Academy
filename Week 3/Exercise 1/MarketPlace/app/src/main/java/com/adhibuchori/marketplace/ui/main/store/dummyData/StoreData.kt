package com.adhibuchori.marketplace.ui.main.store.dummyData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreData(
    val productImageUrl: String,
    val productName: String,
    val productPrice: String,
    val productStore: String,
    val productReview: String
) : Parcelable
