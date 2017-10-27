package com.phoobobo.eyepetizer.ui.view.common

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.durationFormat
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.model.bean.ListItem
import com.phoobobo.eyepetizer.timePreFormat
import kotlinx.android.synthetic.main.item_home_standard.view.*

/**
 * Created by phoobobo on 2017/10/23.
 */
class StandardVideoItem : FrameLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_home_standard, this)
    }

    /**
     * 设置item封面 作者头像 标题 描述
     */
    fun setData(item: Any) {
        val itemData = (item as ListItem).data
        val header = itemData.header
        val content = itemData.content
        val data = content.data

        val cover = data?.cover?.feed
        var avatar = header?.icon
        var avatarRes = R.mipmap.pgc_default_avatar

        Glide.with(context).load(cover).centerCrop().into(iv_cover)

        val ivAvatarCircle = object : BitmapImageViewTarget(iv_avatar) {
            override fun setResource(resource: Bitmap?) {
                super.setResource(resource)
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, resource)
                circularBitmapDrawable.isCircular = true
                iv_avatar.setImageDrawable(circularBitmapDrawable)
            }
        }
        if (avatar == null || "" == avatar) {
            Glide.with(context).load(avatarRes).asBitmap().centerCrop().into(ivAvatarCircle)
        } else {
            Glide.with(context).load(avatar).asBitmap().centerCrop().into(ivAvatarCircle)
        }
        tv_title.text = header?.title
        tv_description.text = header?.description

    }
}