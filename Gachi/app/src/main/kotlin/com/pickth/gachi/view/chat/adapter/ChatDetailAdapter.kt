/*
 * Copyright 2017 Yonghoon Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pickth.gachi.view.chat.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pickth.gachi.R

class ChatDetailAdapter: RecyclerView.Adapter<ChatDetailViewHolder>(), ChatDetailAdapterContract.View, ChatDetailAdapterContract.Model {

    companion object {
        val CHAT_TYPE_ME = 0
        val CHAT_TYPE_OTHER = 1
    }

    private var mItems = ArrayList<ChatMessage>()
    private var mArrRow = listOf<Int>(R.layout.item_chat_message0, R.layout.item_chat_message1)

    override fun onBindViewHolder(holder: ChatDetailViewHolder?, position: Int) {
        holder?.onBind(mItems[position], position)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChatDetailViewHolder {
        val itemView = LayoutInflater
                .from(parent?.context)
                .inflate(mArrRow[viewType], parent, false)

        return ChatDetailViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int = mItems[position].type

    override fun getItem(position: Int): ChatMessage = mItems[position]

    override fun addItem(item: ChatMessage) {
        mItems.add(item)
        notifyItemInserted(itemCount - 1)
    }
}