package com.phoobobo.eyepetizer.ui.view.home.banner

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by phoobobo on 2017/10/24.
 */
class HomeBannerTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val width: Int = page?.width!!
        page.scrollX = (position * width).toInt() / 4 * 3
    }
}