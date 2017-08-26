package com.pickth.gachi.view.main.fragments.chat.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pickth.gachi.R
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.gachi.Gachi
import kotlinx.android.synthetic.main.item_chat.view.*

/**
 * Created by yonghoon on 2017-07-23.
 * Mail   : yonghoon.kim@pickth.com
 */
class ChatViewHolder(val view: View, val clickListener: OnItemClickListener?): RecyclerView.ViewHolder(view) {
    fun onBind(item: Gachi, position: Int) {
        with(itemView) {
            if(item.userImagePath == "") {
                Glide.with(view)
                        .load(R.drawable.test)
                        .apply(RequestOptions().circleCrop())
                        .into(image_chat_thumbnail)
            } else {
                Glide.with(view)
                        .load(item.userImagePath)
                        .apply(RequestOptions().circleCrop())
                        .into(image_chat_thumbnail)
            }


            tv_chat_title.text = item.title
        }

        itemView.setOnClickListener {
            clickListener?.onItemClick(position)
        }
    }
}