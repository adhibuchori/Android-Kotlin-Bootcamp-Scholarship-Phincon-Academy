package com.adhibuchori.narumi.utils

import android.view.View
import androidx.core.view.isVisible

fun View.gone() {
    isVisible = false
}