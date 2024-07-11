package com.adhibuchori.diaryapp.data.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toDateFormat(): String {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return dateFormat.format(this)
}

fun String.getCurrentDay(): String? {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val date = dateFormat.parse(this)
    val currentFormat = SimpleDateFormat("dd", Locale.getDefault())
    return date?.let { currentFormat.format(it) }
}

fun String.getCurrentMonth(): String? {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val date = dateFormat.parse(this)
    val currentFormat = SimpleDateFormat("MMM", Locale.getDefault())
    return date?.let { currentFormat.format(it) }
}

fun String.getCurrentDayText(): String? {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val date = dateFormat.parse(this)
    val currentFormat = SimpleDateFormat("EEE", Locale.getDefault())
    return date?.let { currentFormat.format(it) }
}