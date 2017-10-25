package com.phoobobo.eyepetizer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.ui.view.common.StandardVideoItem
import com.phoobobo.eyepetizer.ui.view.home.HomeTextHeaderItem
import com.phoobobo.eyepetizer.ui.view.home.banner.HomeBanner

/**
 * Created by phoobobo on 2017/10/23.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var itemList: ArrayList<Item> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var bannerItemListCount = 0

    private val TYPE_BANNER = 1
    private val TYPE_STANDARD = 2
    private val TYPE_HEADER_TEXT = 3

    /**
     * 根据position定义view类型
     */
    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_BANNER
        }
        return if (itemList[position + bannerItemListCount - 1].type == "textHeader") {
            TYPE_HEADER_TEXT
        } else {
            TYPE_STANDARD
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            TYPE_BANNER -> (holder?.itemView as HomeBanner)
                    .setData(itemList.take(bannerItemListCount).toCollection(ArrayList()))
            TYPE_STANDARD -> (holder?.itemView as StandardVideoItem).let {
                it.setData(itemList[position + bannerItemListCount - 1], "feed")
            }
            TYPE_HEADER_TEXT -> (holder?.itemView as HomeTextHeaderItem)
                    .setHeaderText(itemList[position + bannerItemListCount - 1].data?.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_BANNER -> ViewHolder(HomeBanner(parent!!.context))
            TYPE_STANDARD -> ViewHolder(StandardVideoItem(parent!!.context))
            TYPE_HEADER_TEXT -> ViewHolder(HomeTextHeaderItem(parent!!.context))
            else -> ViewHolder(null)
        }
    }

    override fun getItemCount(): Int {
        return when {
            itemList.size > bannerItemListCount -> itemList.size - bannerItemListCount + 1
            itemList.size == 0 -> 0
            else -> 1
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}