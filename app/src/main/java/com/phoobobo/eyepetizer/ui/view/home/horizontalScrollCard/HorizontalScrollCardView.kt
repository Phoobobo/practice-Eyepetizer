package com.phoobobo.eyepetizer.ui.view.home.horizontalScrollCard

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import com.phoobobo.eyepetizer.ui.view.common.Indicator
import com.phoobobo.eyepetizer.utils.DisplayManager

/**
 * Created by phoobobo on 2017/10/27.
 */
class HorizontalScrollCardView(context: Context) : FrameLayout(context) {
    private val adapter: ScrollBannerAdapter by lazy { ScrollBannerAdapter() }
    private val viewPager: ViewPager by lazy { ViewPager(context) }
    private val indicators: LinearLayout by lazy { LinearLayout(context) }

    init {
        viewPager.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,
                DisplayManager.dip2px(228f)!!)
        viewPager.adapter = adapter

        indicators.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM// 子View水平居中
        indicators.orientation = LinearLayout.HORIZONTAL
        val layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT)
        indicators.layoutParams = layoutParams

        addView(viewPager)
        addView(indicators)
    }

    internal fun setData(item: Any) {
        adapter.itemList = (item as ListItem).data.itemList
        adapter.viewPager = viewPager
        setIndicators(adapter.itemList!!)
    }

    private fun setIndicators(itemList: ArrayList<Item>) {
        indicators.removeAllViews()
        itemList.forEach { _ ->
            val indicator = Indicator(context)
            val layoutParams = LinearLayout.LayoutParams(DisplayManager.getRealHeight(20)!!, DisplayManager.getRealHeight(20)!!)
            layoutParams.leftMargin = DisplayManager.getRealWidth(10)!!
            layoutParams.rightMargin = DisplayManager.getRealWidth(10)!!
            layoutParams.bottomMargin = DisplayManager.getRealHeight(28)!!
            indicator.layoutParams = layoutParams

            indicators.addView(indicator)
        }
        (indicators.getChildAt(0) as Indicator).setState(true)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until indicators.childCount) {
                    if (i == position) {
                        (indicators.getChildAt(i) as Indicator).setState(true)
                    } else {
                        (indicators.getChildAt(i) as Indicator).setState(false)
                    }
                }
            }

        })
    }
}