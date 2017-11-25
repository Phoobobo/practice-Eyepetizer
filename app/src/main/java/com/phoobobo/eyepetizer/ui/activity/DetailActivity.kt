package com.phoobobo.eyepetizer.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.contract.DetailContract
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.presenter.DetailPresenter
import com.phoobobo.eyepetizer.ui.base.BaseActivity
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by phoobobo on 2017/11/25.
 */
class DetailActivity : BaseActivity(), DetailContract.IView {
    lateinit var mPresenter: DetailPresenter
    lateinit var mData: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mPresenter = DetailPresenter(this)
        loadData()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoPlayer.releaseAllVideos()
    }

    private fun loadData() {
        mData = intent.getSerializableExtra("data") as Item
        mPresenter.setPlayData(mData)
    }

    override fun setupPlayer(url: String?) {
        videoView.setUp(url, false, "")
        videoView.startPlayLogic()
    }
}