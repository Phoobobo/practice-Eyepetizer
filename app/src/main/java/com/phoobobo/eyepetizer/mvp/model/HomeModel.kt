package com.phoobobo.eyepetizer.mvp.model

import com.phoobobo.eyepetizer.io_main
import com.phoobobo.eyepetizer.mvp.model.bean.HomeBean
import com.phoobobo.eyepetizer.network.Network
import io.reactivex.Observable
import java.io.Serializable

/**
 * Created by phoobobo on 2017/10/20.
 */

class HomeModel {
    fun loadFirstData(lastStartId: Int): Observable<HomeBean> {
        return Network.service.getFirstHomeData(lastStartId).io_main()
    }

    // TODO: 优化
    fun loadMoreData(url: String): Observable<HomeBean> {
        return Network.service.getMoreHomeData(url + "&vc=225&vn=3.12.0&deviceModel=Redmi 4").io_main()
    }
}