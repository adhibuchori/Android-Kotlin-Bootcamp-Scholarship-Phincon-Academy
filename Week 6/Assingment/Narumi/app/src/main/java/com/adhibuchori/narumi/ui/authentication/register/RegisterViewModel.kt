package com.adhibuchori.narumi.ui.authentication.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhibuchori.narumi.domain.Resource
import com.adhibuchori.narumi.domain.auth.IAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: IAuthRepository,
) : ViewModel() {
    private val _message = MutableLiveData<String>()

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.register(
                username = name,
                email = email,
                password = password
            )) {
                is Resource.Error -> {
                    _message.postValue(response.errorMessage)
                }

                is Resource.Success -> {
                    _message.postValue(response.data)
                }
            }
        }
    }
}