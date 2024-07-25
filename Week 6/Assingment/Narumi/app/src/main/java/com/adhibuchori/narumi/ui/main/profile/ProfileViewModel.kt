package com.adhibuchori.narumi.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhibuchori.narumi.domain.auth.IAuthRepository
import com.adhibuchori.narumi.domain.auth.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: IAuthRepository,
) : ViewModel() {
    private val _userData = MutableLiveData<UserModel>()
    val userData: LiveData<UserModel> = _userData

    fun fetchProfileData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authRepository.getCurrentUser()
            _userData.postValue(response)
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.signOut()
        }
    }
}