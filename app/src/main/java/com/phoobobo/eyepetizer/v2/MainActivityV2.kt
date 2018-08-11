package com.phoobobo.eyepetizer.v2

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.SparseArray
import androidx.core.util.forEach
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.v2.ui.fragments.*
import kotlinx.android.synthetic.main.activity_main_v2.*
import java.util.*
import java.util.Arrays.asList

private const val defaultFragmentArg = "default";

class MainActivityV2 : AppCompatActivity() {

    private lateinit var fragments: SparseArray<Fragment>
    private var lastFragmentId = R.id.navigation_home
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener replaceFragment(item.itemId, lastFragmentId)
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_v2)
        BottomNavigationViewHelper.disableShiftMode(navigation)
        initFragment()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initFragment() {
        fragments = SparseArray(5)
        fragments.put(R.id.navigation_home, HomeFragment.newInstance(defaultFragmentArg, defaultFragmentArg))
        fragments.put(R.id.navigation_follow, SubscriberFragment.newInstance(defaultFragmentArg, defaultFragmentArg))
        fragments.put(R.id.navigation_add, PublishFragment.newInstance(defaultFragmentArg, defaultFragmentArg))
        fragments.put(R.id.navigation_notifications, NotificationFragment.newInstance(defaultFragmentArg, defaultFragmentArg))
        fragments.put(R.id.navigation_mine, MineFragment.newInstance(defaultFragmentArg, defaultFragmentArg))
        replaceFragment(R.id.navigation_home, lastFragmentId)
    }

    private fun replaceFragment(id: Int, lastFragment: Int): Boolean {
        val beginTransaction = supportFragmentManager.beginTransaction()
                .hide(fragments.get(lastFragment))
        if (!fragments.get(id).isAdded) {
            beginTransaction.add(R.id.fragment_container, fragments.get(id), Integer.toString(id))
        }
        beginTransaction.show(fragments.get(id)).commitAllowingStateLoss()
        lastFragmentId = id
        return true
    }
}
