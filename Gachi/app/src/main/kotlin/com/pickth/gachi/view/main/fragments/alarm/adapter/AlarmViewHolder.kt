package com.pickth.gachi.view.main.fragments.alarm.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pickth.gachi.R
import com.pickth.gachi.util.OnItemClickListener

/**
 * Created by yonghoon on 2017-07-22.
 * Mail   : yonghoon.kim@pickth.com
 */
class AlarmViewHolder(val view: View, val onItemClickListener: OnItemClickListener?): RecyclerView.ViewHolder(view) {
    fun onBind(item: Alarm, position: Int) {
        val tvContent = itemView.findViewById<TextView>(R.id.text_alarm_content)
        val ivThumbnail = itemView.findViewById<ImageView>(R.id.image_alarm_thumbnail)

        Glide.with(view)
                .load(R.drawable.test)
                .apply(RequestOptions().circleCrop())
                .into(ivThumbnail)

        tvContent.text = item.msg

        itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
}