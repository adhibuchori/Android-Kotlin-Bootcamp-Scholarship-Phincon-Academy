package com.adhibuchori.country.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhibuchori.country.domain.CountryModel
import com.adhibuchori.country.domain.ICountryRepository
import com.adhibuchori.country.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ICountryRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            handleState(HomeState.Loading)
            when (val response = repository.fetchCountries()) {
                is Resource.Error -> {
                    Log.d("ERROR", response.errorMessage)
                    handleState(HomeState.Error(response.errorMessage))
                }
                is Resource.Success -> {
                    Log.d("SUCCESS", response.data.toString())
                    handleState(
                        HomeState.Success(response.data, listOf())
                    )
                }
            }
        }
    }

    fun filterCountries(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val currentState = _uiState.first().state) {
                is HomeState.Success -> {
                    val filteredData = currentState.data.filter {
                        it.name.lowercase().contains(search.lowercase())
                    }
                    handleState(
                        HomeState.Success(
                            data = currentState.data,
                            filteredData = if (search.isEmpty()) listOf() else filteredData
                        )
                    )
                }

                else -> {}
            }
        }
    }

    private fun handleState(newState: HomeState) {
        _uiState.getAndUpdate {
            it.copy(
                state = newState
            )
        }
    }
}

sealed class HomeState {
    data class Error(val errorMessage: String) : HomeState()
    data object Loading : HomeState()
    data class Success(
        val data: List<CountryModel>,
        val filteredData: List<CountryModel>,
    ) : HomeState()

    data object Initial : HomeState()
}

data class HomeUIState(
    val state: HomeState = HomeState.Initial,
)