package com.phoobobo.eyepetizer.mvp.presenter

import com.phoobobo.eyepetizer.mvp.contract.HomeContract
import com.phoobobo.eyepetizer.mvp.model.HomeModel
import com.phoobobo.eyepetizer.mvp.model.bean.HomeBean

/**
 * Created by phoobobo on 2017/10/20.
 */
class HomePresenter(homeView: HomeContract.IView) : HomeContract.IPresenter {

    private val mHomeView: HomeContract.IView = homeView

    private val homeModel: HomeModel by lazy {
        HomeModel()
    }

    private var mHomeBean: HomeBean? = null
    private var mNextPageUrl: String? = null

    override fun requestFirstData() {
        homeModel.loadFirstData()
                .flatMap { homeBean ->
                    mHomeBean = homeBean
                    homeModel.loadMoreData(homeBean.nextPageUrl)
                }
                .subscribe({ homeBean ->
                    mNextPageUrl = homeBean.nextPageUrl
                    val newItemList = homeBean.issueList[0].itemList
                    mHomeBean?.issueList!![0].itemList.addAll(newItemList)
                    mHomeView.setFirstData(mHomeBean!!)
                }, { t ->
                    t.printStackTrace()
                    mHomeView.onError()
                })
    }

    override fun requestMoreData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}