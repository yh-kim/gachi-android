package com.pickth.gachi.view.main.fragments.chat.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pickth.gachi.util.OnItemClickListener
import kotlinx.android.synthetic.main.item_chat.view.*

/**
 * Created by yonghoon on 2017-07-23.
 * Mail   : yonghoon.kim@pickth.com
 */
class ChatViewHolder(view: View, clickListener: OnItemClickListener?): RecyclerView.ViewHolder(view) {
    fun onBind(item: Chat, position: Int) {
        with(itemView) {
            tv_chat_title.text = item.title
            tv_chat_title.setTypeface(null, Typeface.BOLD)
        }
    }
}