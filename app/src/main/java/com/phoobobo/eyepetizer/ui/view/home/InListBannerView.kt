package com.phoobobo.eyepetizer.ui.view.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import kotlinx.android.synthetic.main.item_home_in_list_banner.view.*

/**
 * Created by phoobobo on 2017/10/27.
 */
class InListBannerView : FrameLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_home_standard, this)
    }

    internal fun setData(item: Any) {
        val data = (item as Item).data
        val imageUrl = data?.image

        Glide.with(context).load(imageUrl).centerCrop().into(iv_cover)
    }
}