package com.phoobobo.eyepetizer.ui.view.home.horizontalScrollCard

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import com.phoobobo.eyepetizer.ui.view.home.InListBannerView
import com.phoobobo.eyepetizer.ui.view.home.banner.HomeBannerItem

/**
 * Created by phoobobo on 2017/10/27.
 */
class ScrollBannerAdapter : PagerAdapter() {

    private var fakeSize = 0

    internal var viewPager: ViewPager? = null

    var itemList: ArrayList<Item>? = ArrayList()
        set(value) {
            field = value
            fakeSize = field!!.size * 2
            notifyDataSetChanged()
        }
    private var viewList: ArrayList<InListBannerView> = ArrayList()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return if (itemList == null) 0 else fakeSize
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        val truePosition = position % itemList!!.size
//        container?.removeView(viewList[truePosition])
        container?.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val truePosition = position % itemList!!.size
//        if (viewList.size <= truePosition) {
//            val inListBannerView = InListBannerView(container?.context)
//            inListBannerView.setData(itemList!![truePosition])
//            viewList.add(inListBannerView)
//        }
//        val view = viewList[truePosition]
        val inListBannerView = InListBannerView(container?.context)
        inListBannerView.setData(itemList!![truePosition])
        container?.addView(inListBannerView)
        return inListBannerView
    }

//    override fun finishUpdate(container: ViewGroup?) {
//        var position = viewPager?.currentItem
//        if (position == 0) {
//            viewPager?.currentItem = itemList!!.size
//        } else if (position == fakeSize -1) {
//            viewPager?.currentItem = itemList!!.size - 1
//        }
//    }
}