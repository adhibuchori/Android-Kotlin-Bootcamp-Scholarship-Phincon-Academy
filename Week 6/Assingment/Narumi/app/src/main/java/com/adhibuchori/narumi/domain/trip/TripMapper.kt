package com.adhibuchori.narumi.domain.trip

import com.adhibuchori.narumi.data.transaction.model.TransactionEntity
import com.adhibuchori.narumi.data.trip.model.TripResponse

object TripMapper {
    fun mapTripResponseToModel(q: TripResponse) = TripModel(
        uuid = q.uuid,
        tripImage = q.tripImage,
        tripName = q.tripName,
        tripLocation = q.tripLocation,
        tripDate = q.tripDate,
        tripOrderDate = q.tripOrderDate,
        tripDuration = q.tripDuration,
        tripCost = q.tripCost,
        tripDescription = q.tripDescription,
        tripCategory = q.tripCategory
    )

    fun mapTripModelToTransaction(q: TripModel, totalCost: Int) = TransactionEntity(
        uuid = q.uuid,
        tripImage = q.tripImage,
        tripName = q.tripName,
        tripLocation = q.tripLocation,
        tripOrderDate = q.tripOrderDate,
        tripDuration = q.tripDuration,
        tripCost = totalCost
    )
}