package com.adhibuchori.country.data

import com.adhibuchori.country.data.response.CountriesDataResponse
import com.adhibuchori.country.domain.CountryModel

object CountryMapper {
    fun mapDataResponseToModel(q: CountriesDataResponse) = CountryModel(
        name = q.name?.common ?: "",
        nativeName = q.name?.official ?: "",
        population = q.population ?: 0,
        region = q.region ?: "",
        capital = q.capital?.firstOrNull() ?: "",
        flagImageUrl = q.flags?.png ?: "",
        tld = q.tld?.firstOrNull() ?: "",
        borderCountries = q.borders ?: listOf()
    )
}