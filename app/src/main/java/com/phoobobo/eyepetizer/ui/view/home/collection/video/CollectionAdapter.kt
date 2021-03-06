package com.phoobobo.eyepetizer.ui.view.home.collection.video

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.phoobobo.eyepetizer.mvp.model.bean.Item

/**
 * Created by phoobobo on 2017/10/27.
 */
class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    var itemList: ArrayList<Item> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder?.itemView as CollectionItem).setData(itemList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CollectionItem(parent?.context!!))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}