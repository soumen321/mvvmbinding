package com.dogmvvm.utility

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

object ImageUtil {
    fun getPlaceholder(): GradientDrawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setColor(Color.parseColor("#FFFFF0")) //ivory
        return drawable
    }
}