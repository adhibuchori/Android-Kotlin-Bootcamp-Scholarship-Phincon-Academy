package com.adhibuchori.data.auth.source

import com.adhibuchori.data.auth.request.LoginRequest
import com.adhibuchori.data.auth.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPIService {
    @POST
    suspend fun register(
        @Body registerRequest: RegisterRequest
    )

    @POST
    suspend fun login(
        @Body loginRequest: LoginRequest
    )

    @POST
    suspend fun refresh(
        @Body token: RegisterRequest
    )
}