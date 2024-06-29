package com.adhibuchori.marketplace.ui.main.store.adapter

import com.adhibuchori.marketplace.databinding.ItemRowStoreBinding
import com.adhibuchori.marketplace.ui.main.store.dummyData.StoreData
import com.adhibuchori.marketplace.utils.BaseRecyclerViewAdapter
import com.bumptech.glide.Glide

class ListItemStoreAdapter(listStore: List<StoreData>) :
    BaseRecyclerViewAdapter<StoreData, ItemRowStoreBinding>(
        listStore,
        ItemRowStoreBinding::inflate
    ) {
    override fun bindViews(binding: ItemRowStoreBinding, data: StoreData) {
        with(binding) {
            Glide.with(ivRowStoreHorizontalProductImage.context)
                .load(data.productImageUrl)
                .into(ivRowStoreHorizontalProductImage)

            tvRowStoreHorizontalProductName.text = data.productName
            tvRowStoreHorizontalProductPrice.text = data.productPrice
            tvRowStoreHorizontalProductStore.text = data.productStore
            tvRowStoreHorizontalProductReview.text = data.productReview
        }
    }
}