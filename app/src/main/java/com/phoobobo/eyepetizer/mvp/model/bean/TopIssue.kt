package com.phoobobo.eyepetizer.mvp.model.bean

/**
 * Created by phoobobo on 2017/10/26.
 */
data class TopIssue(val type: String, val data: HorizontalScrollCard, val tag: String?)
data class HorizontalScrollCard(val dataType: String, val itemList: ArrayList<Item>, val count: Int)