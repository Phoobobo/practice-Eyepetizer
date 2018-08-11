package com.phoobobo.eyepetizer.v2.ui

import android.app.Fragment
import com.phoobobo.eyepetizer.R

var currentFragment = R.id.rb_home
val tabsId = listOf(R.id.rb_home, R.id.rb_discovery, R.id.rb_subscriber)
abstract class BaseFragment(id: Int) : Fragment() {
    var tabId = 0

    init {
        this.tabId = tabId
    }

    open fun setupToolbar(): Boolean {
        return tabId != currentFragment
    }
}