package com.phoobobo.eyepetizer.mvp.contract

import com.phoobobo.eyepetizer.mvp.base.BasePresenter
import com.phoobobo.eyepetizer.mvp.base.BaseView
import com.phoobobo.eyepetizer.mvp.model.bean.HomeBean
import com.phoobobo.eyepetizer.mvp.model.bean.Item

/**
 * Created by phoobobo on 2017/10/20.
 */
interface HomeContract {

    interface IPresenter: BasePresenter {
        /**
         * 初始化数据
         */
        fun requestFirstData()

        /**
         * 底部加载更多数据
         */
        fun requestMoreData()
    }

    interface IView: BaseView<IPresenter> {
        fun setFirstData(homeBean: HomeBean)
        fun setMoreData(itemList: ArrayList<Item>)
        fun onError()
    }
}