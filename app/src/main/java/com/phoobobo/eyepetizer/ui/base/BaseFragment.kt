package com.phoobobo.eyepetizer.ui.base

import android.support.v4.app.Fragment
import com.phoobobo.eyepetizer.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Created by phoobobo on 2017/10/20.
 */
var currentFragment = R.id.rb_home
val tabsId = listOf(R.id.rb_home, R.id.rb_discovery, R.id.rb_subscriber)

abstract class BaseFragment(tabId: Int) : Fragment(), RxNetManager {
    var tabId = 0

    init {
        this.tabId = tabId
    }

    private val disposables = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    override fun dispose(disposable: Disposable) {
        disposables.remove(disposable)
    }

    override fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    open fun setupToolbar(): Boolean {
        return tabId != currentFragment
    }
}