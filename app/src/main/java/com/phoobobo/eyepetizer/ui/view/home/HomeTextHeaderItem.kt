package com.phoobobo.eyepetizer.ui.view.home

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import com.phoobobo.eyepetizer.utils.DisplayManager

/**
 * Created by phoobobo on 2017/10/24.
 */
class HomeTextHeaderItem : FrameLayout {

    private val textView by lazy { TextView(context) }
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                DisplayManager.dip2px(56f)!!)
        with (textView) {
            gravity = Gravity.CENTER
            textSize = 20f
            setTextColor(Color.BLACK)
            addView(textView)
        }
    }

    internal fun setHeaderText(text: String?) {
        textView.text = text
    }
}