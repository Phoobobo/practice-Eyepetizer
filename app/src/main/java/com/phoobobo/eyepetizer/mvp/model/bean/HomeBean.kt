package com.phoobobo.eyepetizer.mvp.model.bean

/**
 * Created by phoobobo on 2017/10/20.
 */
data class HomeBean (
        var issueList: ArrayList<Issue>,
        val nextPageUrl: String,
        val nextPublishTime: Long,
        val newestIssueType: String,
        val dialog: Any
)