package com.adhibuchori.narumi.ui.authentication.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhibuchori.narumi.domain.Resource
import com.adhibuchori.narumi.domain.auth.IAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: IAuthRepository,
) : ViewModel() {

    private val _message = MutableLiveData<String>()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.login(email = email, password = password)
            Log.d("email", email)
            Log.d("password", password)

            Log.d("LVM Response:", response.toString())
            when (response) {
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