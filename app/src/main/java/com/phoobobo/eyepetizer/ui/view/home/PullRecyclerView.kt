package com.phoobobo.eyepetizer.ui.view.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.phoobobo.eyepetizer.ui.view.home.banner.HomeBanner

/**
 * Created by phoobobo on 2017/10/21.
 */
class PullRecyclerView : RecyclerView {

    var homeBanner: HomeBanner? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        overScrollMode = OVER_SCROLL_NEVER
    }
}