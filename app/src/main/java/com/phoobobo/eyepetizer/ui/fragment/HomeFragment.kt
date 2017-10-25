package com.phoobobo.eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phoobobo.eyepetizer.R
import com.phoobobo.eyepetizer.mvp.contract.HomeContract
import com.phoobobo.eyepetizer.mvp.model.bean.HomeBean
import com.phoobobo.eyepetizer.mvp.model.bean.Item
import com.phoobobo.eyepetizer.mvp.presenter.HomePresenter
import com.phoobobo.eyepetizer.ui.adapter.HomeAdapter
import com.phoobobo.eyepetizer.ui.base.BaseFragment
import com.phoobobo.eyepetizer.ui.base.tabsId
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by phoobobo on 2017/10/21.
 */
class HomeFragment : BaseFragment(tabId = tabsId[0]), HomeContract.IView {

    private var presenter: HomePresenter = HomePresenter(this)

    private val mHomeAdapter: HomeAdapter by lazy { HomeAdapter() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        presenter.requestFirstData()
    }

    private fun initView() {
        rv_home.adapter = mHomeAdapter
        rv_home.layoutManager = LinearLayoutManager(activity)
    }

    override fun setFirstData(homeBean: HomeBean) {
        mHomeAdapter.bannerItemListCount = homeBean.issueList[0].count
        mHomeAdapter.itemList = homeBean.issueList[0].itemList
    }

    override fun setMoreData(itemList: ArrayList<Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupToolbar(): Boolean {
        if (super.setupToolbar()) return true
        return true
    }

}