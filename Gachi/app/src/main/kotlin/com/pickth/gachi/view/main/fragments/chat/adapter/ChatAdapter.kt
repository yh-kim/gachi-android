package com.pickth.gachi.view.main.fragments.chat.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.gachi.Gachi

/**
 * Created by yonghoon on 2017-07-23.
 * Mail   : yonghoon.kim@pickth.com
 */
class ChatAdapter: RecyclerView.Adapter<ChatViewHolder>(), ChatAdapterContract.View, ChatAdapterContract.Model {

    val itemList: ArrayList<Gachi> = ArrayList()
    var mOnItemClickListener: OnItemClickListener?= null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_chat, parent, false)

        return ChatViewHolder(itemView, mOnItemClickListener)
    }

    override fun onBindViewHolder(holder: ChatViewHolder?, position: Int) {
        holder?.onBind(getItem(position), position)
    }

    override fun getItemCount(): Int = itemList.size

    override fun setItemClickListener(clickListener: OnItemClickListener) {
        mOnItemClickListener = clickListener
    }

    override fun getItem(position: Int): Gachi = itemList[position]

    override fun addItem(item: Gachi) {
        itemList.add(item)
        notifyItemInserted(itemCount - 1)
    }

}