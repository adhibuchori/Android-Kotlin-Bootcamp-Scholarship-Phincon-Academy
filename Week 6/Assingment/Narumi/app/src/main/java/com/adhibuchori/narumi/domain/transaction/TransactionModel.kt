package com.adhibuchori.narumi.domain.transaction

data class TransactionModel(
    val uuid: String,
    val tripImage: Int,
    val tripName: String,
    val tripLocation: String,
    val tripOrderDate: String,
    val tripDuration: String,
    val tripCost: Int,
)