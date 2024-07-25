package com.adhibuchori.narumi.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhibuchori.narumi.domain.auth.IAuthRepository
import com.adhibuchori.narumi.domain.auth.UserModel
import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.domain.trip.TripModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tripRepository: ITripRepository,
    private val authRepository: IAuthRepository
) : ViewModel() {

    private val _allTrips = MutableLiveData<List<TripModel>>()
    val allTrips = _allTrips

    private val _userData = MutableLiveData<UserModel>()
    val userData = _userData

    fun fetchAllData() {
        fetchTrips()
        fetchProfileData()
    }

    private fun fetchTrips() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = tripRepository.fetchTrips()
            _allTrips.postValue(response)
        }
    }

    private fun fetchProfileData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.getCurrentUser()
            _userData.postValue(response)
        }
    }
}