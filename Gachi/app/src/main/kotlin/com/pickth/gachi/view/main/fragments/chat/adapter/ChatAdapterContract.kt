package com.pickth.gachi.view.main.fragments.chat.adapter

import com.pickth.gachi.util.OnItemClickListener

/**
 * Created by yonghoon on 2017-07-23.
 * Mail   : yonghoon.kim@pickth.com
 */
interface ChatAdapterContract {
    interface View {
        fun setItemClickListener(clickListener: OnItemClickListener)
    }

    interface Model {
        fun getItem(position: Int): Chat

        fun addItem(item: Chat)
    }
}