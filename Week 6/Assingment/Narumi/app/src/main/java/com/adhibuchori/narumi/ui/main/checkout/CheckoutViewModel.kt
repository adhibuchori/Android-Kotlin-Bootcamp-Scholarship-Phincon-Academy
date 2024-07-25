package com.adhibuchori.narumi.ui.main.checkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhibuchori.narumi.domain.transaction.ITransactionRepository
import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.domain.trip.TripModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val tripRepository: ITripRepository,
    private val transactionRepository: ITransactionRepository,
) : ViewModel() {
    private val _tripData = MutableLiveData<TripModel>()
    val tripData = _tripData

    private val _itemPax = MutableLiveData(1)
    val itemPax = _itemPax

    fun fetchTripById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = tripRepository.fetchTripById(id)
            response?.let {
                _tripData.postValue(it)
            }
        }
    }

    fun increaseItemPax() {
        _itemPax.value = _itemPax.value?.plus(1)
    }

    fun decreaseItemPax() {
        _itemPax.value = if ((_itemPax.value ?: 1) > 1) _itemPax.value?.minus(1) else 1
    }

    fun countTotalCost(itemPax: Int): Int = _tripData.value?.tripCost?.times(itemPax) ?: 0

    fun checkout() {
        viewModelScope.launch(Dispatchers.IO) {
            val tripReserved = _tripData.value!!
            transactionRepository.checkout(
                tripReserved,
                tripReserved.tripCost.times(_itemPax.value!!)
            )
        }
    }
}