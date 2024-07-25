package com.adhibuchori.narumi.ui.main.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.domain.trip.TripModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val tripRepository: ITripRepository,
) : ViewModel() {
    private val _tripData = MutableLiveData<TripModel>()
    val tripData = _tripData

    fun fetchTripById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = tripRepository.fetchTripById(id)
            response?.let {
                _tripData.postValue(it)
            }
        }
    }
}