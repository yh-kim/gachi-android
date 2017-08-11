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

package com.pickth.gachi.view.main.fragments.festival.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.util.OnItemClickListener

class FestivalAdapter: RecyclerView.Adapter<FestivalViewHolder>(), FestivalAdapterContract.View, FestivalAdapterContract.Model {

    private var items = ArrayList<Festival>()
    var mOnItemClickListener: OnItemClickListener?= null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FestivalViewHolder {
        val itemView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_main_festival, parent, false)

        return FestivalViewHolder(itemView, mOnItemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FestivalViewHolder?, position: Int) {
        holder?.onBind(items[position], position)
    }

    override fun setItemClickListener(clickListener: OnItemClickListener) {
        mOnItemClickListener = clickListener
    }

    override fun addItem(item: Festival) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }
}