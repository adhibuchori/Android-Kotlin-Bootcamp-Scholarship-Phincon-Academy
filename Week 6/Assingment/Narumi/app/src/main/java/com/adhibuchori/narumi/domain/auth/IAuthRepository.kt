package com.adhibuchori.narumi.domain.auth

import androidx.lifecycle.LiveData
import com.adhibuchori.narumi.domain.Resource

interface IAuthRepository {
    suspend fun login(email: String, password: String): Resource<String>
    suspend fun register(username: String, email: String, password: String): Resource<String>
    suspend fun signOut()

    fun getCurrentUser(): UserModel
    fun observeLoginState(): LiveData<Boolean>
    fun clearObserver()
}