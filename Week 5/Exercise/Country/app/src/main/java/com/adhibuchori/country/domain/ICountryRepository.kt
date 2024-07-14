package com.adhibuchori.country.domain

interface ICountryRepository {
    suspend fun fetchCountries(): Resource<List<CountryModel>>
}