package com.adhibuchori.data.store.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<String?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)
