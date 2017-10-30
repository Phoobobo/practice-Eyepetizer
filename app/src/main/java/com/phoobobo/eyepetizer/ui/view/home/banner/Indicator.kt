package com.phoobobo.eyepetizer.ui.view.home.banner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

/**
 * Created by phoobobo on 2017/10/30.
 */
class Indicator(context: Context) : View(context) {
    private val paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    internal fun setState(selected: Boolean) {
        paint.color = if (selected) 0xffffffff.toInt() else 0x88ffffff.toInt()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        measuredHeight
        measuredWidth
        canvas!!.translate((measuredWidth / 2).toFloat(), (measuredHeight / 2).toFloat())
        canvas.drawCircle(0f, 0f, (measuredWidth / 2).toFloat(), paint)
    }
}