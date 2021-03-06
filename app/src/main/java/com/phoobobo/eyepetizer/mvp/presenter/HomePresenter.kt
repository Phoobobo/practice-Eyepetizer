package com.phoobobo.eyepetizer.mvp.presenter

import android.util.Log
import com.phoobobo.eyepetizer.Storage
import com.phoobobo.eyepetizer.mvp.contract.HomeContract
import com.phoobobo.eyepetizer.mvp.model.HomeModel
import com.phoobobo.eyepetizer.mvp.model.bean.HomeBean

/**
 * Created by phoobobo on 2017/10/20.
 */
class HomePresenter(homeView: HomeContract.IView) : HomeContract.IPresenter {

    private val TAG = "HomePresenter"

    private val mHomeView: HomeContract.IView = homeView

    private val homeModel: HomeModel by lazy {
        HomeModel()
    }

    private var wholeList: ArrayList<Any>? = ArrayList()

    private var mNextPageUrl: String? = null

    override fun requestFirstData() {
        homeModel.loadFirstData(Storage.lastStartId)
                .subscribe({ homeBean ->
                    mNextPageUrl = homeBean.nextPageUrl
                    Storage.lastStartId = homeBean.lastStartId

                    wholeList?.clear() // 不清空，刷新的时候会无限累加到既有数组的尾部！
                    wholeList?.add(homeBean.topIssue)
                    wholeList?.addAll(homeBean.itemList)

                    mHomeView.setFirstData(wholeList!!)
                    mHomeView.notifyRefreshCount(homeBean.refreshCount)

                }, { t ->
                    t.printStackTrace()
                    mHomeView.onError()
                })
    }

    override fun requestMoreData() {
        mNextPageUrl?.let {
            homeModel.loadMoreData(it).subscribe({ homeBean: HomeBean ->

                mHomeView.setMoreData(homeBean.itemList)
                mNextPageUrl = homeBean.nextPageUrl
            })
        }
    }
}