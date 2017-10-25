package com.phoobobo.eyepetizer.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.ui.base.BaseActivity
import com.phoobobo.eyepetizer.ui.base.currentFragment
import com.phoobobo.eyepetizer.ui.base.tabsId
import com.phoobobo.eyepetizer.ui.fragment.DiscoveryFragment
import com.phoobobo.eyepetizer.ui.fragment.HomeFragment
import com.phoobobo.eyepetizer.ui.fragment.SubscriberFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRadio()
    }

    private fun setRadio() {
        rb_home.isChecked = true
        chooseFragment(R.id.rb_home)
        rg_root.setOnCheckedChangeListener { _, checkedId -> chooseFragment(checkedId)}
    }

    private fun chooseFragment(checkedId: Int) {
        currentFragment = checkedId

        val beginTransaction = supportFragmentManager.beginTransaction()

        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(checkedId.toString())

        if (fragment == null) {
            when (checkedId) {
                R.id.rb_home -> beginTransaction.add(R.id.fragment_content, HomeFragment(), checkedId.toString())
                R.id.rb_discovery -> beginTransaction.add(R.id.fragment_content, DiscoveryFragment(), checkedId.toString())
                R.id.rb_subscriber -> beginTransaction.add(R.id.fragment_content, SubscriberFragment(), checkedId.toString())
            }
        }

        tabsId.forEach { tab ->
            val aFragment = supportFragmentManager.findFragmentByTag(tab.toString())

            if (tab == checkedId) {
                aFragment?.let {
                    beginTransaction.show(it)
                }
            } else {
                aFragment?.let {
                    beginTransaction.hide(it)
                }
            }
        }

        beginTransaction.commit()

    }

}
