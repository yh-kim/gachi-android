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

    companion object {
        val ALARM_TYPE_BASE = 0
        val ALARM_TYPE_BUTTON = 1
    }

    val mItems: ArrayList<Alarm> = ArrayList()
    private var mArrRow = listOf<Int>(R.layout.item_alarm0, R.layout.item_alarm1)
    var onItemClickListener: OnItemClickListener ?= null

    override fun setOnClickListener(clickListener: OnItemClickListener) {
        this.onItemClickListener = clickListener
    }

    override fun getItem(position: Int) = mItems[position]

    override fun addItem(item: Alarm) {
        mItems.add(item)
        notifyItemInserted(itemCount - 1)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AlarmViewHolder {
        val itemView = LayoutInflater
                .from(parent?.context)
                .inflate(mArrRow[viewType], parent, false)
        return AlarmViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder?, position: Int) {
        holder?.onBind(getItem(position), position)
    }

    override fun getItemViewType(position: Int): Int = mItems[position].type

    override fun getItemCount(): Int = mItems.size
}