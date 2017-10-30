package com.phoobobo.eyepetizer.ui.view.home.banner

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.TopIssue
import com.phoobobo.eyepetizer.utils.DisplayManager
import kotlinx.android.synthetic.main.top_banner_home.view.*

/**
 * Created by phoobobo on 2017/10/24.
 */
class HomeBanner : FrameLayout {

    private val bannerAdapter: BannerAdapter by lazy { BannerAdapter() }
    private val indicators: LinearLayout by lazy { LinearLayout(context) }
    // TODO: 悬浮文字和Slogan

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        initListeners()
    }

    internal fun setData(topIssue: Any) {
        bannerAdapter.data = (topIssue as TopIssue).data.itemList
//        setIndicators(itemList)
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

        View.inflate(context, R.layout.top_banner_home, this)
        vp.adapter = bannerAdapter
        vp.setPageTransformer(true, HomeBannerTransformer())
    }
}