package com.adhibuchori.data.payment.response

import com.google.gson.annotations.SerializedName

data class PaymentResponse(

    @field:SerializedName("code")
	val code: Int? = null,

    @field:SerializedName("data")
	val data: List<DataItem?>? = null,

    @field:SerializedName("message")
	val message: String? = null
)

data class ItemItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem(

    @field:SerializedName("item")
	val item: List<ItemItem?>? = null,

    @field:SerializedName("title")
	val title: String? = null
)
