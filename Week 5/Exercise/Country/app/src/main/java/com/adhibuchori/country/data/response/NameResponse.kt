package com.adhibuchori.country.data.response

import com.google.gson.annotations.SerializedName

data class NameResponse(

	@field:SerializedName("common")
	val common: String? = null,

	@field:SerializedName("official")
	val official: String? = null
)