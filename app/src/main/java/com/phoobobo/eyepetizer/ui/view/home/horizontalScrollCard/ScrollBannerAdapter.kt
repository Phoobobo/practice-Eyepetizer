package com.phoobobo.eyepetizer.ui.view.home.horizontalScrollCard

import android.support.v4.view.PagerAdapter
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

    var itemList: ArrayList<Item>? = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var viewList: ArrayList<InListBannerView> = ArrayList()

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return if (itemList == null) 0 else itemList!!.size
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(viewList[position])
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        if (viewList.size <= position) {
            val inListBannerView = InListBannerView(container?.context)
            inListBannerView.setData(itemList!![position])
            viewList.add(inListBannerView)
        }
        val view = viewList[position]
        container?.addView(view)
        return view
    }
}