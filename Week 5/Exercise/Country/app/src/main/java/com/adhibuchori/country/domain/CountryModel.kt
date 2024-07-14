package com.adhibuchori.country.domain

data class CountryModel(
    val name: String,
    val nativeName: String,
    val population: Int,
    val region: String,
    val capital: String,

    val flagImageUrl: String,
    val tld: String,
    val borderCountries: List<String>
) {
    companion object {
        val dummy = CountryModel(
            name = "Phyllis Pace",
            nativeName = "Kent Clarke",
            population = 2174,
            region = "viris",
            capital = "vix",
            flagImageUrl = "http://www.bing.com/search?q=tritani",
            tld = "dolor",
            borderCountries = listOf("France", "Germany")
        )
        val empty = CountryModel (
            name = "",
            nativeName = "",
            population = 0,
            region = "",
            capital = "",
            flagImageUrl = "",
            tld = "",
            borderCountries = listOf()

        )
    }
}