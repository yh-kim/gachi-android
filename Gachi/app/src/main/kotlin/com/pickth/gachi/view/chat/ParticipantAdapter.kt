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

package com.pickth.gachi.view.chat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pickth.gachi.R
import kotlinx.android.synthetic.main.item_participant.view.*

/**
 * Created by yonghoon on 2017-08-24
 */

class ParticipantAdapter: RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder>() {
    private var mItems = ArrayList<Participant>()

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        holder.onBind(mItems[position], position)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        var itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_participant, parent, false)

        return ParticipantViewHolder(itemView)
    }

    fun addItem(item: Participant) {
        mItems.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun getItem(position: Int) = mItems[position]

    class ParticipantViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun onBind(item: Participant, position: Int) {
            with(itemView) {
                if(item.imageUrl == "") {
                    Glide.with(context)
                            .load(R.drawable.test)
                            .apply(RequestOptions().circleCrop())
                            .into(iv_participant_image)
                } else {
                    Glide.with(context)
                            .load(item.imageUrl)
                            .apply(RequestOptions().circleCrop())
                            .into(iv_participant_image)
                }
                tv_participant_nickname.text = item.name
            }
        }
    }
}