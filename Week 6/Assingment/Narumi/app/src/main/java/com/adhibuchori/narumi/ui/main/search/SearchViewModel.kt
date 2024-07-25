package com.adhibuchori.narumi.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.domain.trip.TripModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val tripRepository: ITripRepository,
) : ViewModel() {

    private val _allTrips = MutableLiveData<List<TripModel>>()

    private val _filterTrips = MutableLiveData<List<TripModel>>()
    val filterTrips = _filterTrips

    fun fetchAllTrips() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = tripRepository.fetchTrips()
            _allTrips.postValue(response)
        }
    }

    fun searchTrips(query: String) {
        if (query.isEmpty()) {
            _allTrips.postValue(_allTrips.value)
        } else {
            val filteredList = _allTrips.value?.filter {
                it.tripName.contains(query, ignoreCase = true) || it.tripLocation.contains(
                    query,
                    ignoreCase = true
                )
            } ?: listOf()
            _filterTrips.postValue(filteredList)
        }
    }
}