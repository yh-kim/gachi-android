package com.pickth.gachi.view.main.fragments.alarm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.util.OnItemClickListener

/**
 * Created by yonghoon on 2017-07-22.
 * Mail   : yonghoon.kim@pickth.com
 */
class AlarmAdapter: RecyclerView.Adapter<AlarmViewHolder>()
        , AlarmAdapterContract.View, AlarmAdapterContract.Model{

    val itemList: ArrayList<Alarm> = ArrayList()
    var onItemClickListener: OnItemClickListener ?= null

    override fun setOnClickListener(clickListener: OnItemClickListener) {
        this.onItemClickListener = clickListener
    }

    override fun getItem(position: Int) = itemList[position]

    override fun addItem(item: Alarm) {
        itemList.add(item)
        notifyItemInserted(itemCount - 1)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AlarmViewHolder {
        val itemView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_alarm, parent, false)
        return AlarmViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder?, position: Int) {
        holder?.onBind(getItem(position), position)
    }

    override fun getItemCount(): Int = itemList.size
}