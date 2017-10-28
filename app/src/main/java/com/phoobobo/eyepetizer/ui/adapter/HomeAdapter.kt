package com.phoobobo.eyepetizer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.phoobobo.eyepetizer.mvp.model.bean.HomeBean
import com.phoobobo.eyepetizer.mvp.model.bean.HorizontalScrollCard
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import com.phoobobo.eyepetizer.ui.view.common.StandardVideoItem
import com.phoobobo.eyepetizer.ui.view.home.InListBannerView
import com.phoobobo.eyepetizer.ui.view.home.banner.HomeBanner
import com.phoobobo.eyepetizer.ui.view.home.collection.video.CollectionView
import com.phoobobo.eyepetizer.ui.view.home.collection.video.SquareCollectionView
import com.phoobobo.eyepetizer.ui.view.home.horizontalScrollCard.HorizontalScrollCardView

/**
 * Created by phoobobo on 2017/10/23.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var itemList: ArrayList<Any> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var bannerItemListCount = 0

    private val TYPE_TOP_ISSUE = 1
    private val TYPE_STANDARD = 2
    private val TYPE_BANNER = 3 // 列表中的banner
    private val TYPE_VIDEO_COLLECTION = 4
    private val TYPE_SQUARE_COLLECTION = 5
    private val TYPE_HORIZONTAL_SCROLL_CARD = 6 // 列表内自动播放的banner

    fun addData(itemList: ArrayList<Item>) {
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    /**
     * 根据position定义view类型
     */
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) { // 手动放到第一位
            TYPE_TOP_ISSUE
        } else {
            when ((itemList[position] as ListItem).type) {
                HomeBean.videoCollectionWithCover -> TYPE_VIDEO_COLLECTION
                HomeBean.banner -> TYPE_BANNER
                HomeBean.squareCardCollection -> TYPE_SQUARE_COLLECTION
                HomeBean.horizontalScrollCard -> TYPE_HORIZONTAL_SCROLL_CARD
                else -> TYPE_STANDARD
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemViewType = getItemViewType(position)
        when (itemViewType) {
            TYPE_TOP_ISSUE -> (holder?.itemView as HomeBanner)
                    .setData(itemList[position])
            TYPE_STANDARD -> (holder?.itemView as StandardVideoItem)
                    .setData(itemList[position])
            TYPE_BANNER -> (holder?.itemView as InListBannerView)
                    .setData(itemList[position])
            TYPE_VIDEO_COLLECTION -> (holder?.itemView as CollectionView)
                    .setData(itemList[position])
            TYPE_SQUARE_COLLECTION -> (holder?.itemView as SquareCollectionView)
                    .setData(itemList[position])
            TYPE_HORIZONTAL_SCROLL_CARD -> (holder?.itemView as HorizontalScrollCardView)
                    .setData(itemList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_TOP_ISSUE -> ViewHolder(HomeBanner(parent!!.context))
            TYPE_BANNER -> ViewHolder(InListBannerView(parent!!.context))
            TYPE_STANDARD -> ViewHolder(StandardVideoItem(parent!!.context))
            TYPE_VIDEO_COLLECTION -> ViewHolder(CollectionView(parent!!.context))
            TYPE_SQUARE_COLLECTION -> ViewHolder(SquareCollectionView(parent!!.context))
            TYPE_HORIZONTAL_SCROLL_CARD -> ViewHolder(HorizontalScrollCardView(parent!!.context))
            else -> ViewHolder(null)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}