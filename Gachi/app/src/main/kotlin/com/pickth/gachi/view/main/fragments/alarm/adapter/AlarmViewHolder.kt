package com.pickth.gachi.view.main.fragments.alarm.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pickth.gachi.util.OnItemClickListener
import kotlinx.android.synthetic.main.item_alarm.view.*

/**
 * Created by yonghoon on 2017-07-22.
 * Mail   : yonghoon.kim@pickth.com
 */
class AlarmViewHolder(view: View, val onItemClickListener: OnItemClickListener?): RecyclerView.ViewHolder(view) {
    fun onBind(item: Alarm, position: Int) {
        with(itemView) {
            text_alarm_content.text = item.msg
        }
    }
}