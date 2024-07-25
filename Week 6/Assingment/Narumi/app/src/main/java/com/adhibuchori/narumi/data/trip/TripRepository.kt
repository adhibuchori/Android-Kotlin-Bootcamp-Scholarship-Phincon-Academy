package com.adhibuchori.narumi.data.trip

import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.domain.trip.TripMapper.mapTripResponseToModel
import com.adhibuchori.narumi.domain.trip.TripModel

class TripRepository: ITripRepository {
    private val source = DummyDataProvider.getTripResponses()
    override fun fetchTrips(): List<TripModel> {
        return source.map {
            mapTripResponseToModel(it)
        }
    }

    override fun fetchTripByCategory(category: String): List<TripModel> {
        return source.filter { it.tripCategory == category }.map {
            mapTripResponseToModel(it)
        }
    }

    override fun fetchTripById(id: String): TripModel? = source.find { it.uuid == id }?.let {
        mapTripResponseToModel(it)
    }
}