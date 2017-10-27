package com.phoobobo.eyepetizer.network

import com.phoobobo.eyepetizer.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by phoobobo on 2017/10/20.
 */
interface ApiService {
    /**
     * banner+一页数据，num=1
     * TODO: 动态设定和获取device以及版本信息
     */
    @GET("v4/tabs/selected?vc=225&vn=3.12.0&deviceModel=Redmi 4")
    fun getFirstHomeData(@Query("lastStartId") lastStartId: Int = -1): Observable<HomeBean>

    /**
     * 根据nextpageurl请求数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Observable<HomeBean>


//    /**
//     * issue里面包了itemlist和nextpageurl
//     */
//    @GET
//    fun getIssue(@Url url: String): Observable<Issue>
//
//    /**
//     * 热门的类别
//     */
//    @GET
//    fun getHotCategory(@Url url: String): Observable<HotCategory>
//
//
//    /**
//     * 获取回复
//     */
//    @GET("v2/replies/video?")
//    fun getReply(@Query("videoId") videoId: Long): Observable<Issue>
//
//    /**
//     * 根据item id获取相关视频
//     */
//    @GET("v4/video/related?")
//    fun getRelatedData(@Query("id") id: Long): Observable<Issue>
//
//    /**
//     * 获取分类
//     */
//    @GET("v4/categories")
//    fun getCategory(): Observable<ArrayList<Category>>
//
//    /**
//     * 获取分类下的全部数据
//     */
//    @GET("v4/categories/videoList")
//    fun getCategoryItemList(@Query("id") id: Long): Observable<Issue>
}