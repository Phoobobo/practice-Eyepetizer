package com.phoobobo.eyepetizer.ui.view.home.collection.video

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.phoobobo.eyepetizer.mvp.model.bean.Item

/**
 * Created by phoobobo on 2017/10/27.
 */
class SquareCollectionAdapter : RecyclerView.Adapter<SquareCollectionAdapter.ViewHolder>() {

    var itemList: ArrayList<Item> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        (holder?.itemView as SquareCollectionItem).setData(itemList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(SquareCollectionItem(parent?.context!!))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}