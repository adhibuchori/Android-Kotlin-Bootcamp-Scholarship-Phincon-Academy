package com.adhibuchori.animationapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhibuchori.animationapp.databinding.ItemRowDisneyQuotesBinding

class ItemDisneyQuotesAdapter(private val listQuotes: ArrayList<DisneyQuote>) : RecyclerView.Adapter<ItemDisneyQuotesAdapter.QuoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemRowDisneyQuotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val (quoteText, quoteAuthor, quoteSource) = listQuotes[position]
        holder.bind(quoteText, quoteAuthor, quoteSource)
    }

    override fun getItemCount(): Int = listQuotes.size

    class QuoteViewHolder(private val binding: ItemRowDisneyQuotesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quoteText: String, quoteAuthor: String, quoteSource: String) {
            binding.tvQuoteText.text = quoteText
            binding.tvQuoteAuthor.text = quoteAuthor
            binding.tvQuoteSource.text = quoteSource
        }
    }
}