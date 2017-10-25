package com.phoobobo.eyepetizer.ui.view.home.banner

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import kotlinx.android.synthetic.main.item_home_banner.view.*

/**
 * Created by phoobobo on 2017/10/24.
 */
class HomeBannerItem : FrameLayout {
    lateinit var data: Item
    constructor(context: Context?, data: Item) : super(context){
        this.data = data
        initView()
        setupView()
    }

    private fun initView() {
        val view = View.inflate(context, R.layout.item_home_banner, null)
        addView(view)
    }

    private fun setupView() {
        val feedImgUrl = data.data?.cover?.feed
        Glide.with(context).load(feedImgUrl).centerCrop().into(imageView)
    }
}