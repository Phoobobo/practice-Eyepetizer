package com.phoobobo.eyepetizer.ui.view.home.collection.video

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import kotlinx.android.synthetic.main.item_home_square_collecion_item.view.*

/**
 * Created by phoobobo on 2017/10/27.
 */
class SquareCollectionItem(context: Context): FrameLayout(context) {
    init {
        View.inflate(context, R.layout.item_home_square_collecion_item, this)
    }
    fun setData(item: Item) {
        val data = item.data
        val coverImgUrl = data?.image
        val title = data?.title
        Glide.with(context).load(coverImgUrl).centerCrop().into(iv_cover)
        tv_categary_name.text = title
    }
}