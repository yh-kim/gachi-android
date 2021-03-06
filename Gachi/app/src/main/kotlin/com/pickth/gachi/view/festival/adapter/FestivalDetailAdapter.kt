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

package com.pickth.gachi.view.festival.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pickth.gachi.R
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.gachi.Gachi
import kotlinx.android.synthetic.main.item_festival_gachi.view.*

class FestivalDetailAdapter(val listener: OnItemClickListener): RecyclerView.Adapter<FestivalDetailAdapter.FestivalDetailViewHolder>() {

    private var items = ArrayList<Gachi>()

    override fun onBindViewHolder(holder: FestivalDetailViewHolder?, position: Int) {
        holder?.onBInd(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FestivalDetailViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_festival_gachi, parent, false)

        return FestivalDetailViewHolder(itemView, listener)
    }

    fun addItem(item: Gachi) {
        Log.d("Gachi__FestivalGachi", "addItem item: ${item}")
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun getItem(position: Int) = items[position]

    class FestivalDetailViewHolder(val view: View, val listener: OnItemClickListener): RecyclerView.ViewHolder(view) {
        fun onBInd(item: Gachi, position: Int) {
            with(itemView) {
                setOnClickListener {
                    listener.onItemClick(position)
                }

                if(item.title == "null") {
                    tv_festival_gachi_title.text = "제목이 없습니다"
                } else {
                    tv_festival_gachi_title.text = item.title
                }

                if(item.userImagePath == "") {
                    Glide.with(view)
                            .load(R.drawable.test)
                            .apply(RequestOptions().circleCrop())
                            .into(iv_festival_gachi)
                } else {
                    Glide.with(view)
                            .load(item.userImagePath)
                            .apply(RequestOptions().circleCrop())
                            .into(iv_festival_gachi)
                }

            }

        }
    }
}