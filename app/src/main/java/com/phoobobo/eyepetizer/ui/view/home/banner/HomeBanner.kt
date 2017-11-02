package com.phoobobo.eyepetizer.ui.view.home.banner

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.*
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.TopIssue
import com.phoobobo.eyepetizer.ui.view.common.JumpShowTextView
import com.phoobobo.eyepetizer.utils.DisplayManager
import kotlinx.android.synthetic.main.top_banner_home.view.*

/**
 * Created by phoobobo on 2017/10/24.
 */
class HomeBanner : FrameLayout {

    private var currentIndex = 0
    private val bannerAdapter: BannerAdapter by lazy { BannerAdapter() }
    private val indicators: LinearLayout by lazy { LinearLayout(context) }
    private val tvTitle: JumpShowTextView by lazy { JumpShowTextView(context) }
    private val tvSlogan: JumpShowTextView by lazy { JumpShowTextView(context) }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        initListeners()
    }

    internal fun setData(topIssue: Any) {
        val list = (topIssue as TopIssue).data.itemList
        bannerAdapter.data = list
        setIndicators(list)
        setTitleSlogan(0)
    }

    private fun setTitleSlogan(position: Int) {
        currentIndex = position
        val bannerItemData = bannerAdapter.data!![position]
        tvTitle.text = bannerItemData.data?.title
        tvSlogan.text = bannerItemData.data?.slogan
    }

    private fun setIndicators(itemList: ArrayList<Item>) {
        indicators.removeAllViews()
        itemList.forEach { _ ->
            val indicator = Indicator(context)
            val layoutParams = LinearLayout.LayoutParams(DisplayManager.getRealHeight(20)!!, DisplayManager.getRealHeight(20)!!)
            layoutParams.leftMargin = DisplayManager.getRealWidth(10)!!
            layoutParams.rightMargin = DisplayManager.getRealWidth(10)!!
            indicator.layoutParams = layoutParams
            indicator.setState(false)

            indicators.addView(indicator)
        }
        (indicators.getChildAt(0) as Indicator).setState(true)
    }

    private fun initListeners() {
        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                setTitleSlogan(position)

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

    private fun initView() {

        View.inflate(context, R.layout.top_banner_home, this)
        vp.adapter = bannerAdapter
        vp.setPageTransformer(true, HomeBannerTransformer())

        val floatInfo = LinearLayout(context)
        floatInfo.gravity = Gravity.CENTER_HORIZONTAL
        floatInfo.orientation = LinearLayout.VERTICAL
        val floatInfoParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        floatInfoParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        floatInfo.layoutParams = floatInfoParams

        val homePageHeaderIcon = ImageView(context)
        homePageHeaderIcon.setImageResource(R.mipmap.home_page_header_icon)
        homePageHeaderIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
        homePageHeaderIcon.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(110)!!)

        tvTitle.textSize = 52f
        tvTitle.color = Color.WHITE
        tvTitle.isBold = true
        tvTitle.marginBottom=DisplayManager.dip2px(5f)?.toFloat()!!
        tvTitle.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)

        tvSlogan.textSize = 33f
        tvSlogan.color = Color.WHITE
        tvSlogan.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)

        indicators.gravity = Gravity.CENTER_HORIZONTAL
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayManager.getRealHeight(20)!!)
        layoutParams.topMargin = DisplayManager.getRealHeight(30)!!
        layoutParams.bottomMargin = DisplayManager.dip2px(65f)!!

        indicators.layoutParams = layoutParams
        indicators.orientation = LinearLayout.HORIZONTAL

        with(floatInfo) {
            addView(homePageHeaderIcon)
            addView(tvTitle)
            addView(tvSlogan)
            addView(indicators)
        }

        addView(floatInfo)
    }

    internal fun getViewPager() : ViewPager = vp
}