package com.adhibuchori.diaryapp.utils

import android.view.View
import androidx.core.view.isVisible

fun View.gone() {
    isVisible = false
}