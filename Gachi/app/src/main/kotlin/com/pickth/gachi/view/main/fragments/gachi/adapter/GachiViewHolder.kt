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

package com.pickth.gachi.view.main.fragments.gachi.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pickth.gachi.R
import kotlinx.android.synthetic.main.item_gachi.view.*

class GachiViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    fun onBind(item: Gachi, position: Int) {
        with(itemView) {
            Glide.with(view)
                    .load(R.drawable.test)
                    .apply(RequestOptions().circleCrop())
                    .into(image_gachi_thumbnail)

            tv_gachi_title.text = item.title
            tv_gachi_title.setTypeface(null, Typeface.BOLD)

            rv_gachi_reliability.setReliability(item.reliability)
        }
    }
}