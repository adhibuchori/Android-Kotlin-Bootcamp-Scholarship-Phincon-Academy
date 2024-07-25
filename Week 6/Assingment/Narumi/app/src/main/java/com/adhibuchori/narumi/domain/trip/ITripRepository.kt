package com.adhibuchori.narumi.domain.trip

interface ITripRepository {
    fun fetchTrips(): List<TripModel>
    fun fetchTripByCategory(category: String): List<TripModel>
    fun fetchTripById(id: String): TripModel?
}