package com.pickth.gachi.view.main.fragments.alarm.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pickth.gachi.R
import com.pickth.gachi.util.OnItemClickListener
import kotlinx.android.synthetic.main.item_alarm.view.*

/**
 * Created by yonghoon on 2017-07-22.
 * Mail   : yonghoon.kim@pickth.com
 */
class AlarmViewHolder(val view: View, val onItemClickListener: OnItemClickListener?): RecyclerView.ViewHolder(view) {
    fun onBind(item: Alarm, position: Int) {
        with(itemView) {
            Glide.with(view)
                    .load(R.drawable.test)
                    .apply(RequestOptions().circleCrop())
                    .into(image_alarm_thumbnail)

            text_alarm_content.text = item.msg
        }

        itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
}