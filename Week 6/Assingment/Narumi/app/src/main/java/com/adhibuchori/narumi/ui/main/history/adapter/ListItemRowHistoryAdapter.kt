package com.adhibuchori.narumi.ui.main.history.adapter

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import com.adhibuchori.narumi.databinding.ItemRowTripHistoryBinding
import com.adhibuchori.narumi.domain.transaction.TransactionModel
import com.adhibuchori.narumi.utils.base.BaseRecyclerViewAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class ListItemRowHistoryAdapter(listTripHistory: List<TransactionModel>) :
    BaseRecyclerViewAdapter<TransactionModel, ItemRowTripHistoryBinding>(
        listTripHistory,
        ItemRowTripHistoryBinding::inflate
    ) {
    override fun bindViews(binding: ItemRowTripHistoryBinding, data: TransactionModel) {
        with(binding) {
            sivItemRowHistoryTripImage.setImageResource(data.tripImage)
            tvItemRowHistoryTripName.text = data.tripName
            tvItemRowHistoryTripLocation.text = data.tripLocation
            tvItemRowHistoryTripOrderDate.text = data.tripOrderDate
            tvItemRowHistoryTripDuration.text = data.tripDuration
            tvItemRowHistoryTripCost.text = formatCurrency(data.tripCost)

            val formattedOrderDate = formatOrderDate(data.tripOrderDate)
            val orderDateText = "Order Date: $formattedOrderDate"
            val spannableString = SpannableString(orderDateText)
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                "Order Date:".length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tvItemRowHistoryTripOrderDate.text = spannableString
        }
    }

    private fun formatCurrency(amount: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        format.maximumFractionDigits = 0
        return format.format(amount)
    }

    private fun formatOrderDate(orderDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        val date = inputFormat.parse(orderDate)
        return outputFormat.format(date)
    }
}