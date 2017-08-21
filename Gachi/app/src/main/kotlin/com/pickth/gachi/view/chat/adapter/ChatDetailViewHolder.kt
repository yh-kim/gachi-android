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
import android.view.View
import android.widget.TextView
import com.pickth.gachi.R

class ChatDetailViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun onBind(item: ChatMessage, position: Int) {
        val tvDay = itemView.findViewById<TextView>(R.id.tv_chat_message_day)
        val tvMessage = itemView.findViewById<TextView>(R.id.tv_chat_message_msg)

        tvDay.text = item.date
        tvMessage.text= item.msg
    }
}