package com.adhibuchori.marketplace.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhibuchori.marketplace.databinding.SlideItemContainerBinding

class IntroSliderAdapter(private val introSlides: List<IntroSlide>) : RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>() {

    inner class IntroSliderViewHolder(private val binding: SlideItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(introSlide: IntroSlide) {
            binding.ivSliderContent.setImageResource(introSlide.sliderContent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
        val binding = SlideItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroSliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    override fun getItemCount(): Int = introSlides.size
}
