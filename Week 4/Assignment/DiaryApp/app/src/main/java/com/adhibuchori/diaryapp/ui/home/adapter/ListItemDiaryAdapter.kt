package com.adhibuchori.diaryapp.ui.home.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.adhibuchori.diaryapp.data.database.DiaryData
import com.adhibuchori.diaryapp.data.helper.getCurrentDay
import com.adhibuchori.diaryapp.data.helper.getCurrentDayText
import com.adhibuchori.diaryapp.data.helper.getCurrentMonth
import com.adhibuchori.diaryapp.databinding.ItemRowDiaryBinding

class ListItemDiaryAdapter : RecyclerView.Adapter<ListItemDiaryAdapter.DiaryViewHolder>() {
    private val listDiaryData = ArrayList<DiaryData>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListDiaryData(listDiaryData: List<DiaryData>) {
        this.listDiaryData.clear()
        this.listDiaryData.addAll(listDiaryData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val binding =
            ItemRowDiaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(listDiaryData[position])

        @Suppress("DEPRECATION")
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDiaryData[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listDiaryData.size
    }

    inner class DiaryViewHolder(private val binding: ItemRowDiaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(diaryData: DiaryData) {
            with(binding) {
                tvItemRowDiaryTitleData.text = diaryData.title
                tvItemRowDiaryDescriptionData.text = diaryData.description
                tvItemRowDiaryDateData.text = diaryData.date?.getCurrentDay()
                tvItemRowDiaryDayData.text = diaryData.date?.getCurrentDayText()
                tvItemRowDiaryMonthData.text = diaryData.date?.getCurrentMonth()

                cbItemRowDiary.isChecked = diaryData.isChecked

                if (diaryData.isChecked) {
                    tvItemRowDiaryTitleData.paintFlags =
                        tvItemRowDiaryTitleData.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    tvItemRowDiaryDescriptionData.paintFlags =
                        tvItemRowDiaryDescriptionData.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    tvItemRowDiaryTitleData.paintFlags =
                        tvItemRowDiaryTitleData.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    tvItemRowDiaryDescriptionData.paintFlags =
                        tvItemRowDiaryDescriptionData.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }

                cbItemRowDiary.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
                    onItemClickCallback.onItemChecked(diaryData, isChecked)
                }
                cvItemRowDiaryDeleteIcon.setOnClickListener {
                    onItemClickCallback.onItemDeleted(diaryData)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(diaryData: DiaryData)
        fun onItemChecked(diaryData: DiaryData, checked: Boolean)
        fun onItemDeleted(diaryData: DiaryData)
    }
}