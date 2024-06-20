package com.adhibuchori.animationapp

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DisneyQuote(
    val quoteText: String,
    val quoteAuthor: String,
    val quoteSource: String
) : Parcelable
