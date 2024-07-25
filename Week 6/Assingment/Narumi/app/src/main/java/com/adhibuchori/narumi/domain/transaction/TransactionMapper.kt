package com.adhibuchori.narumi.domain.transaction

import com.adhibuchori.narumi.data.transaction.model.TransactionEntity

object TransactionMapper {
    fun mapEntityToModel(q: TransactionEntity) = TransactionModel(
        uuid = q.uuid,
        tripImage = q.tripImage,
        tripName = q.tripName,
        tripLocation = q.tripLocation,
        tripOrderDate = q.tripOrderDate,
        tripDuration = q.tripDuration,
        tripCost = q.tripCost
    )
}