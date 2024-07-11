package com.adhibuchori.diaryapp.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class DiaryData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "diary_title")
    var title: String? = null,

    @ColumnInfo(name = "diary_description")
    var description: String? = null,

    @ColumnInfo(name = "diary_done")
    var isChecked: Boolean = false,

    @ColumnInfo(name = "diary_date")
    var date: String? = null,
) : Parcelable