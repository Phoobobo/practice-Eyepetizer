package com.phoobobo.eyepetizer.mvp.presenter

import com.phoobobo.eyepetizer.mvp.contract.DetailContract
import com.phoobobo.eyepetizer.mvp.model.bean.Item

/**
 * Created by phoobobo on 2017/11/25.
 */
class DetailPresenter(view: DetailContract.IView) : DetailContract.IPresenter {

    private val mDetailView = view
    override fun setPlayData(itemData: Item) {
        val url = itemData.data?.playUrl
        mDetailView.setupPlayer(url)
    }
}