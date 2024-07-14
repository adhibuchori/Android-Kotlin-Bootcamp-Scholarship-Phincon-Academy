package com.adhibuchori.country.data

import com.adhibuchori.country.data.response.CountriesDataResponse
import retrofit2.http.GET

interface CountryApiService {
    @GET("all")
    suspend fun fetchCountries(): List<CountriesDataResponse>?
}