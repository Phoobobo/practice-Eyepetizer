package com.phoobobo.eyepetizer.ui.view.home.banner

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.phoobobo.eyepetizer.mvp.model.bean.Item

/**
 * Created by phoobobo on 2017/10/24.
 */
class BannerAdapter : PagerAdapter() {

    var data: ArrayList<Item>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var viewList: ArrayList<HomeBannerItem> = ArrayList()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        viewList[position].releasePlayer()
        container?.removeView(viewList[position])
    }

    override fun getCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (viewList.size <= position) {
            val homeBannerItem = HomeBannerItem(container?.context, data!![position])
            viewList.add(homeBannerItem)
        }
        val view = viewList[position]
        container?.addView(view)
        view.play()
        return view
    }

}