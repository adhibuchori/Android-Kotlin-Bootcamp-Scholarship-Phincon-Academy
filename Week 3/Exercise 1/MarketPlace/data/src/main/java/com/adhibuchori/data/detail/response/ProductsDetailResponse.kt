package com.adhibuchori.data.detail.response

import com.google.gson.annotations.SerializedName

data class ProductsDetailResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ProductVariantItem(

	@field:SerializedName("variantPrice")
	val variantPrice: Int? = null,

	@field:SerializedName("variantName")
	val variantName: String? = null
)

data class Data(

	@field:SerializedName("image")
	val image: List<String?>? = null,

	@field:SerializedName("productId")
	val productId: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("totalRating")
	val totalRating: Int? = null,

	@field:SerializedName("store")
	val store: String? = null,

	@field:SerializedName("productName")
	val productName: String? = null,

	@field:SerializedName("totalSatisfaction")
	val totalSatisfaction: Int? = null,

	@field:SerializedName("sale")
	val sale: Int? = null,

	@field:SerializedName("productVariant")
	val productVariant: List<ProductVariantItem?>? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("productRating")
	val productRating: Int? = null,

	@field:SerializedName("brand")
	val brand: String? = null,

	@field:SerializedName("productPrice")
	val productPrice: Int? = null,

	@field:SerializedName("totalReview")
	val totalReview: Int? = null
)
