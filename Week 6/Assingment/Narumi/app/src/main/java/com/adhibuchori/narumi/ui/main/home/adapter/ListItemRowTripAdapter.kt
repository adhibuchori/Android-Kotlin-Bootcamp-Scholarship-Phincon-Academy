package com.adhibuchori.narumi.ui.main.home.adapter

import android.annotation.SuppressLint
import com.adhibuchori.narumi.databinding.ItemRowTripBinding
import com.adhibuchori.narumi.domain.trip.TripModel
import com.adhibuchori.narumi.utils.base.BaseRecyclerViewAdapter

class ListItemRowTripAdapter(private var listTrip: List<TripModel>) :
    BaseRecyclerViewAdapter<TripModel, ItemRowTripBinding>(
        listTrip,
        ItemRowTripBinding::inflate
    ) {
    override fun bindViews(binding: ItemRowTripBinding, data: TripModel) {
        with(binding) {
            sivItemRowTripImage.setImageResource(data.tripImage)
            tvItemRowTripName.text = data.tripName
            tvItemRowTripLocation.text = data.tripLocation
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<TripModel>) {
        listTrip = newList
        notifyDataSetChanged()
    }
}