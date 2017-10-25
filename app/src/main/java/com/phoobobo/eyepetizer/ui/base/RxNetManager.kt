package com.phoobobo.eyepetizer.ui.base

import io.reactivex.disposables.Disposable

/**
 * Created by phoobobo on 2017/10/20.
 */
interface RxNetManager {
    fun dispose(disposable: Disposable)
    fun addDisposable(disposable: Disposable)
}