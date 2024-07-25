package com.adhibuchori.narumi.ui

import androidx.lifecycle.ViewModel
import com.adhibuchori.narumi.domain.auth.IAuthRepository

class MainViewModel(
    authRepository: IAuthRepository,
) : ViewModel() {
    val authState = authRepository.observeLoginState()
}