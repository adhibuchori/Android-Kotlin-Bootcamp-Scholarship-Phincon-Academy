package com.adhibuchori.narumi.data.transaction.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey
    val uuid: String,
    val tripImage: Int,
    val tripName: String,
    val tripLocation: String,
    val tripOrderDate: String,
    val tripDuration: String,
    val tripCost: Int,
)