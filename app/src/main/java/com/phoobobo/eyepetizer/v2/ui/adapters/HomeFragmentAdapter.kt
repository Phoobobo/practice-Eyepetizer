package com.phoobobo.eyepetizer.v2.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.phoobobo.eyepetizer.v2.ui.fragments.HomeChildFragment

class HomeFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return HomeChildFragment.newInstance("hah", "haa")
    }

    override fun getCount(): Int {
        return 10
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "OBJECT " + (position + 1)
    }

}