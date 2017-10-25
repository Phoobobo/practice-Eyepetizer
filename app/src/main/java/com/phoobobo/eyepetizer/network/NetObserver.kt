package com.phoobobo.eyepetizer.network

import com.phoobobo.eyepetizer.ui.base.BaseActivity
import com.phoobobo.eyepetizer.ui.base.BaseFragment
import io.reactivex.observers.DisposableObserver

/**
 * Created by phoobobo on 2017/10/20.
 */
class NetObserver<T> : DisposableObserver<T> {

    var activity: BaseActivity? = null
    var fragment: BaseFragment? = null
    var onSuccess: OnSuccess? = null

    interface OnSuccess {
        fun onSuccess()
    }

    constructor(activity: BaseActivity?, onSuccess: OnSuccess) : super() {
        this.activity = activity
        this.onSuccess = onSuccess
    }

    constructor(fragment: BaseFragment?, onSuccess: OnSuccess) : super() {
        this.fragment = fragment
        this.onSuccess = onSuccess
    }

    override fun onComplete() {
        activity?.dispose(this)
        fragment?.dispose(this)
    }

    override fun onNext(t: T) {
        onSuccess?.onSuccess()
    }

    override fun onStart() {
        super.onStart()
        activity?.dispose(this)
        fragment?.dispose(this)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        activity?.dispose(this)
        fragment?.dispose(this)
    }
}