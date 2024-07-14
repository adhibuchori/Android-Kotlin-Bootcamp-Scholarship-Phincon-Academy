package com.adhibuchori.country.data.response

import com.google.gson.annotations.SerializedName

data class CountriesDataResponse(

    @field:SerializedName("capital")
	val capital: List<String>? = null,

    @field:SerializedName("borders")
	val borders: List<String>? = null,

    @field:SerializedName("flags")
	val flags: FlagsResponse? = null,

    @field:SerializedName("name")
	val name: NameResponse? = null,

    @field:SerializedName("tld")
	val tld: List<String?>? = null,

    @field:SerializedName("population")
	val population: Int? = null,

    @field:SerializedName("region")
    val region: String? = null
)