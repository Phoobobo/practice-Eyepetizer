package com.phoobobo.eyepetizer.mvp.model.bean

/**
 * Created by phoobobo on 2017/10/23.
 */
data class Issue (
        val releaseTime: Long,
        val type: String,
        val date: Long,
        val total: Int,
        val publishTime: Long,
        val itemList: ArrayList<Item>,
        val count: Int,
        val nextPageUrl: String
)