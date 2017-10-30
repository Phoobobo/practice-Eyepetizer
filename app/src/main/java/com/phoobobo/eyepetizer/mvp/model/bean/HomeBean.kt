package com.phoobobo.eyepetizer.mvp.model.bean

/**
 * Created by phoobobo on 2017/10/20.
 */
data class HomeBean (
        var topIssue: TopIssue, // banner
        var itemList: ArrayList<ListItem>, // list
        var count: Int,
        var total: Int,
        var date: Long,
        val nextPageUrl: String,
        val nextPublishTime: Long,
        val dialog: Any,
        val refreshCount: Int,
        val lastStartId: Int
) {
    companion object {
        val followCard = "followCard"
        val videoCollectionWithCover = "videoCollectionWithCover"
        val banner = "banner"
        val banner3 = "banner3"
        val squareCardCollection = "squareCardCollection"
        val horizontalScrollCard = "horizontalScrollCard"
    }
}