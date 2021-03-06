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

package com.pickth.gachi.view.main.fragments.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival
import com.pickth.gachi.view.main.fragments.festival.adapter.SearchViewHolder

class SearchAdapter: RecyclerView.Adapter<SearchViewHolder>(), SearchAdapterContract.View, SearchAdapterContract.Model {

    private var items = ArrayList<Festival>()
    var mOnItemClickListener: OnItemClickListener?= null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_search_festival, parent, false)
        return SearchViewHolder(itemView, mOnItemClickListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder?, position: Int) {
        holder?.onBind(items[position], position)
    }

    override fun setItemClickListener(clickListener: OnItemClickListener) {
        mOnItemClickListener = clickListener
    }

    override fun addItem(item: Festival) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    override fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Festival = items[position]
}