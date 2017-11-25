package com.phoobobo.eyepetizer.mvp.contract

import com.phoobobo.eyepetizer.mvp.base.BasePresenter
import com.phoobobo.eyepetizer.mvp.base.BaseView
import com.phoobobo.eyepetizer.mvp.model.bean.Item

/**
 * Created by phoobobo on 2017/11/25.
 */
interface DetailContract {
    interface IPresenter: BasePresenter {
        fun setPlayData(itemData: Item)
    }

    interface IView : BaseView<IPresenter> {
        fun setupPlayer(url: String?)
    }
}