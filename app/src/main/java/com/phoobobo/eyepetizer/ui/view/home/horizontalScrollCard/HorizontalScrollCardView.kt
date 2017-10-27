package com.phoobobo.eyepetizer.ui.view.home.horizontalScrollCard

import android.content.Context
import android.support.v4.view.ViewPager
import android.widget.FrameLayout
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import com.phoobobo.eyepetizer.utils.DisplayManager

/**
 * Created by phoobobo on 2017/10/27.
 */
class HorizontalScrollCardView(context: Context) : FrameLayout(context) {
    private val adapter: ScrollBannerAdapter by lazy { ScrollBannerAdapter() }
    private val viewPager: ViewPager by lazy { ViewPager(context) }

    init {
        viewPager.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(500)!!)
        viewPager.adapter = adapter
        addView(viewPager)
    }

    internal fun setData(item: Any) {
        adapter.itemList = (item as ListItem).data.itemList
    }
}