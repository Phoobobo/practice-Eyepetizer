package com.phoobobo.eyepetizer.ui.view.home.collection.video

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import kotlinx.android.synthetic.main.item_home_square_collection_view.view.*

/**
 * Created by phoobobo on 2017/10/27.
 */
class SquareCollectionView : FrameLayout {

    private val collectionAdapter = SquareCollectionAdapter()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.item_home_square_collection_view, this)
        rv_item_list.adapter = collectionAdapter
    }

    internal fun setData(data: Any) {
        val item = data as ListItem
        iv_title.text = item.data.header.title
        collectionAdapter.itemList = item.data.itemList!!
    }
}