package com.adhibuchori.narumi.ui.main.checkout.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhibuchori.narumi.data.checkout.PaymentItem
import com.adhibuchori.narumi.databinding.ItemRowPaymentMethodBinding
import com.bumptech.glide.Glide

class PaymentMethodAdapter :
    RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder>() {

    private var onItemClickListener: ((PaymentItem) -> Unit)? = null
    private val list = mutableListOf<PaymentItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<PaymentItem>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val binding =
            ItemRowPaymentMethodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentMethodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class PaymentMethodViewHolder(private val binding: ItemRowPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PaymentItem) {
            binding.tvItemRowPaymentMethodName.text = item.label
            Glide.with(itemView.context)
                .load(item.image)
                .into(binding.ivItemRowPaymentMethodImage)
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    fun setOnItemClickListener(listener: (PaymentItem) -> Unit) {
        onItemClickListener = listener
    }
}