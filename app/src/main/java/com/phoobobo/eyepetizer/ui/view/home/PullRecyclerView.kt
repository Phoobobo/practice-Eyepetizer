package com.phoobobo.eyepetizer.ui.view.home

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.ui.fragment.HomeFragment
import com.phoobobo.eyepetizer.ui.view.home.banner.HomeBanner

/**
 * Created by phoobobo on 2017/10/21.
 */
class PullRecyclerView : RecyclerView, HomeFragment.DataRefreshListener {

    private val TAG = "PullRecyclerView"
    private val MAX_PULL_DISTANCE = 300

    private var mOriginY = -1f
    private var mOriginX = -1f
    private var mCanRefresh = false
    private var mPulling = false
    private var mHasShowLoading = false
    private var mNeedRecover = false
    private var mOriginWidth = 0
    private var mOriginHeight = 0
    private val mTargetView by lazy { (getChildAt(0) as HomeBanner).getViewPager() }
    private var mOnRefreshListener: OnRefreshListener? = null

    private val mLoadingIcon by lazy {
        val imageView = ImageView(context)
        imageView.setImageResource(R.mipmap.eye_loading_progress)
        imageView
    }

    private val mLoadingView by lazy {
        val frameLayout = RelativeLayout(context)
        frameLayout.setBackgroundColor(0xaa000000.toInt())
        frameLayout.gravity = Gravity.CENTER
        frameLayout.addView(mLoadingIcon)
        frameLayout.layoutParams = ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT)
        frameLayout
    }

    private val mLoadingAnimation by lazy {
        val rotateAnimation = RotateAnimation(0f, 365f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 500
        rotateAnimation.repeatCount = -1
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        overScrollMode = OVER_SCROLL_NEVER
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        var shouldIntercept = super.onInterceptTouchEvent(e)
        // 如果垂直方向不能向下滑动，即我们可以做下拉刷新操作的基础
        if (!canScrollVertically(-1)) {
            when (e?.action) {
                MotionEvent.ACTION_DOWN -> {
                    mOriginY = e.y
                    mOriginX = e.x
                    val layoutParams = mTargetView.layoutParams
                    mOriginWidth = width
                    mOriginHeight = layoutParams.height
                }
                MotionEvent.ACTION_MOVE -> {
                    if (Math.abs(e.y - mOriginY) > Math.abs(e.x - mOriginX)) { // 当前正在垂直方向滑动
                        shouldIntercept = true // 拦截Move事件交由本View的onTouchEvent处理
                        mPulling = true
                    }
                }
            }
        }
        return shouldIntercept
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        if (canScrollVertically(-1)) return super.onTouchEvent(e)
        when (e?.action) {
            MotionEvent.ACTION_MOVE -> {
                if (mPulling) {
                    val deltaY = e.y.minus(mOriginY)
                    changeHeaderScale(deltaY)
                    return true // 不能把这个滑动动作再返回给RecyclerView处理
                }
            }
            MotionEvent.ACTION_UP -> {
                if (mNeedRecover)
                    animateRecover()
            }
        }
        return super.onTouchEvent(e)
    }

    private fun animateRecover() {
        val layoutParams = mTargetView.layoutParams
        val headerRecoverAnimator = ValueAnimator.ofInt(layoutParams.height, mOriginHeight)
        with (headerRecoverAnimator) {
            addUpdateListener { animation ->
                val heightInUpdating = animation.animatedValue as Int
                layoutParams.height = heightInUpdating
                layoutParams.width = layoutParams.width * heightInUpdating / mOriginHeight
                mTargetView.layoutParams = layoutParams
                val dx = layoutParams.width - mOriginWidth
                mTargetView.translationX = -(dx * 1f / 2)
            }
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    if (mCanRefresh) {
                        mOnRefreshListener?.onRefresh()
                    } else {
                        hideLoadingView()
                    }
                    reset()
                }

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationStart(animation: Animator?) {}

            })
            duration = 100
            start()
        }
    }

    private fun reset() {
        mPulling = false
        mCanRefresh = false
    }

    private fun changeHeaderScale(deltaY: Float) {
        if (deltaY > 0) {
            mNeedRecover = true

            val ratio = (1f / (0.004 * deltaY + 1)).toFloat()//实现阻尼效果 TODO: 研究阻尼效果的实现，阻尼曲线
            val dY = deltaY * ratio
            val layoutParams = mTargetView.layoutParams
            layoutParams.height = (mOriginHeight + dY).toInt()
            layoutParams.width = (mOriginWidth + mOriginWidth * (dY / mOriginHeight)).toInt()
            mTargetView.layoutParams = layoutParams
            val dx = layoutParams.width - mOriginWidth
            mTargetView.translationX = -(dx * 1f / 2) // 补偿由于放大导致的水平偏移量

            if (!mHasShowLoading) {
                showLoadingIcon(getChildAt(0) as HomeBanner)
            }
            setLoadingScale(deltaY)
        }
    }

    private fun showLoadingIcon(viewGroup: ViewGroup) {
        mHasShowLoading = true
        viewGroup.addView(mLoadingView)
    }

    private fun hideLoadingView() {
        mHasShowLoading = false
        mLoadingAnimation.cancel()
        (getChildAt(0) as HomeBanner).removeView(mLoadingView)
    }

    private fun setLoadingScale(deltaY: Float) {
        if (mCanRefresh) return // 一旦到达阈值则不再缩放
        var scale: Float = (deltaY - 150) / MAX_PULL_DISTANCE
        if (scale >= 1) {
            scale = 1f
            mCanRefresh = true
            mLoadingIcon.startAnimation(mLoadingAnimation)
        }
        mLoadingIcon.scaleX = scale
        mLoadingIcon.scaleY = scale
    }

    interface OnRefreshListener {
        fun onRefresh()
    }

    internal fun setOnRefreshListener(listener: OnRefreshListener) {
        mOnRefreshListener = listener
    }

    override fun onEndRefresh(count: Int) {
        hideLoadingView()
        Toast.makeText(context, "更新了$count 条内容", Toast.LENGTH_SHORT).show()
    }
}