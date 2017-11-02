package com.phoobobo.eyepetizer.ui.view.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import kotlinx.android.synthetic.main.item_home_in_list_banner.view.*

/**
 * Created by phoobobo on 2017/10/27.
 */
class InListBannerView(context: Context?) : LinearLayout(context) {

    init {
        View.inflate(context, R.layout.item_home_in_list_banner, this)
    }

    internal fun setData(item: Any) {
        val data = (item as Item).data
        val imageUrl = data?.image

        Glide.with(context).load(imageUrl).centerCrop().into(iv_cover)
    }
}