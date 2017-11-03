package com.phoobobo.eyepetizer.ui.view.home.banner

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.ui.view.home.NoControllerVideo
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.item_home_banner.view.*

/**
 * Created by phoobobo on 2017/10/24.
 */
class HomeBannerItem : FrameLayout {
    var data: Item
    constructor(context: Context?, data: Item) : super(context){
        this.data = data
        initView()
        setupView()
    }

    private fun initView() {
        val view = View.inflate(context, R.layout.item_home_banner, null)
        addView(view)
    }

    private fun setupView() {
        val feedImgUrl = data.data?.cover?.feed
        val thumbPlayUrl = data.data?.thumbPlayUrl

        imageView.visibility = View.VISIBLE
        Glide.with(context).load(feedImgUrl).centerCrop().into(imageView)

        if (thumbPlayUrl == null || thumbPlayUrl == "") {
            videoView.visibility = View.GONE
        } else {
            videoView.visibility = View.VISIBLE
            videoView.setUp(thumbPlayUrl, true, "")
            GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL)
        }
    }

    private var isVideoInitialed = false
    private val TAG = this.javaClass.simpleName

    private fun initVideo() {
        isVideoInitialed= true
        videoView.setVideoAllCallBack(object : NoControllerVideo.NoControllerVideoViewCallBack() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onPrepared");//加载成功
                imageView.visibility = View.INVISIBLE
            }

            override fun onAutoComplete(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onAutoComplete");//播放完成
                imageView.visibility = View.VISIBLE
                videoView.startPlayLogic()

            }

            override fun onPlayError(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onPlayError");
                imageView.visibility = View.VISIBLE
                videoView.startPlayLogic()
            }
        })
    }

    internal fun play() {
        if (!isVideoInitialed && videoView.visibility == View.VISIBLE) {
            videoView.startPlayLogic()
            initVideo()
        }
    }

    internal fun releasePlayer() {
        isVideoInitialed = false
        if (videoView.visibility == View.VISIBLE) {
            videoView.setVideoAllCallBack(null)
            GSYVideoPlayer.releaseAllVideos()
        }
    }
}