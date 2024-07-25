package com.adhibuchori.narumi.ui.main.home.adapter

import com.adhibuchori.narumi.databinding.ItemRowTripPopularDestinationBinding
import com.adhibuchori.narumi.domain.trip.TripModel
import com.adhibuchori.narumi.utils.base.BaseRecyclerViewAdapter

class ListItemRowTripPopularDestinationAdapter(listTripPopularDestination: List<TripModel>) :
    BaseRecyclerViewAdapter<TripModel, ItemRowTripPopularDestinationBinding>(
        listTripPopularDestination,
        ItemRowTripPopularDestinationBinding::inflate
    ) {
    override fun bindViews(binding: ItemRowTripPopularDestinationBinding, data: TripModel) {
        with(binding) {
            sivItemRowPopularDestinationTripImage.setImageResource(data.tripImage)
            tvItemRowPopularDestinationTripName.text = data.tripName
            tvItemRowPopularDestinationTripLocation.text = data.tripLocation
        }
    }
}