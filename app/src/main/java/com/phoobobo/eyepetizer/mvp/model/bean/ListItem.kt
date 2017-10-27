package com.phoobobo.eyepetizer.mvp.model.bean

/**
 * Created by phoobobo on 2017/10/26.
 * 注意可能包括Banner
 */
data class ListItem(val type: String, // followCard, banner, or videoCollectionWithCover
                    val data: FollowCard, val tag: String?)
data class FollowCard(val dataType: String, // FollowCard or Banner
                      val header: FollowCardHeader,
                      val content: Item,
                      val id: Int?,
                      val title: String?,
                      val description: String?,
                      val image: String?,
                      val actionUrl: String?,
                      val shade: Boolean?,
                      val label: Label?,
                      val labelList: ArrayList<Any>?,
                      val itemList: ArrayList<Item>?,
                      val adTrack: Any?)
data class FollowCardHeader(
        val id: Int,
        val title: String,
        val font: Any?,
        val cover: Any?,
        val label: Any?,
        val actionUrl: String,
        val labelList: ArrayList<Any?>,
        val icon: String,
        val iconType: String,
        val description: String,
        val time: Long
)
data class Label(val text: String, val card: String, val detail: String?)