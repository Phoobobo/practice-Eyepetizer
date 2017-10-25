package com.phoobobo.eyepetizer.ui.view.home.banner

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.utils.DisplayManager

/**
 * Created by phoobobo on 2017/10/24.
 */
class HomeBanner : FrameLayout {

    private val bannerAdapter: BannerAdapter by lazy { BannerAdapter() }
    private val viewPager: ViewPager by lazy { ViewPager(context) }
    private val indicators: LinearLayout by lazy { LinearLayout(context) }
    // TODO: 悬浮文字和Slogan

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        initListeners()
    }

    internal fun setData(itemList: ArrayList<Item>) {
        itemList.filter { item -> item.type == "banner2" }.forEach { item -> itemList.remove(item) }
        bannerAdapter.data = itemList
        setIndicators(itemList)
        setTitleSlogan(0)
    }

    private fun setTitleSlogan(position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setIndicators(itemList: ArrayList<Item>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initListeners() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initView() {
        viewPager.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(810)!!)
        viewPager.adapter = bannerAdapter
        viewPager.setPageTransformer(true, HomeBannerTransformer())

        addView(viewPager)
    }
}