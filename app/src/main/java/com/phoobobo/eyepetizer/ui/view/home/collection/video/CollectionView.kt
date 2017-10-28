package com.phoobobo.eyepetizer.ui.view.home.collection.video

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import kotlinx.android.synthetic.main.item_home_collection_view.view.*

/**
 * Created by phoobobo on 2017/10/27.
 */
class CollectionView : FrameLayout {

    private val collectionAdapter = CollectionAdapter()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.item_home_collection_view, this)
        rv_item_list.adapter = collectionAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_item_list.layoutManager = linearLayoutManager
    }

    internal fun setData(data: Any) {
        val item = data as ListItem
        val coverUrl = item.data.header.cover
        Glide.with(context).load(coverUrl).centerCrop().into(iv_cover)

        collectionAdapter.itemList = item.data.itemList!!
    }
}