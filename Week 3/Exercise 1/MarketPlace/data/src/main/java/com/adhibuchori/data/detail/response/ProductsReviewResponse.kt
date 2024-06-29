package com.adhibuchori.data.detail.response

import com.google.gson.annotations.SerializedName

data class ProductsReviewResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("userImage")
	val userImage: String? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("userReview")
	val userReview: String? = null,

	@field:SerializedName("userRating")
	val userRating: Int? = null
)
