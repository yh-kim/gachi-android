package com.pickth.gachi.view.main.fragments.alarm.adapter

import com.pickth.gachi.util.OnItemClickListener

/**
 * Created by yonghoon on 2017-07-22.
 * Mail   : yonghoon.kim@pickth.com
 */
interface AlarmAdapterContract {
    interface View {
        fun setOnClickListener(clickListener: OnItemClickListener)
    }

    interface Model {
        fun getItem(position: Int): Alarm

        fun addItem(item: Alarm)

        fun getItemCount(): Int
    }
}