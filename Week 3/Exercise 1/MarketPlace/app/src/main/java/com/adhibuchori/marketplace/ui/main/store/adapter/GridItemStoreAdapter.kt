package com.adhibuchori.marketplace.ui.main.store.adapter

import com.adhibuchori.marketplace.databinding.ItemRowStoreGridBinding
import com.adhibuchori.marketplace.ui.main.store.dummyData.StoreData
import com.adhibuchori.marketplace.utils.BaseRecyclerViewAdapter
import com.bumptech.glide.Glide

class GridItemStoreAdapter(listStore: List<StoreData>) :
    BaseRecyclerViewAdapter<StoreData, ItemRowStoreGridBinding>(
        listStore,
        ItemRowStoreGridBinding::inflate
    ) {
    override fun bindViews(binding: ItemRowStoreGridBinding, data: StoreData) {
        with(binding) {
            Glide.with(ivRowStoreGridProductImage.context)
                .load(data.productImageUrl)
                .into(ivRowStoreGridProductImage)

            tvRowStoreGridProductName.text = data.productName
            tvRowStoreGridProductPrice.text = data.productPrice
            tvRowStoreGridProductStore.text = data.productStore
            tvRowStoreGridProductReview.text = data.productReview
        }
    }
}