package com.phoobobo.eyepetizer.ui.view.home

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView

/**
 * Created by phoobobo on 2017/10/30.
 */
class UnknownView(context: Context) : TextView(context) {
    init {
        layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        textSize = 12f
        setTextColor(Color.RED)
        text = "unknown view"
    }
}