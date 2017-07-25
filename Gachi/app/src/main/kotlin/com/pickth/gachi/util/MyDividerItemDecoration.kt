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

package com.pickth.gachi.util

import android.content.Context
import android.graphics.Canvas
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.pickth.gachi.R

class MyDividerItemDecoration(context: Context): RecyclerView.ItemDecoration() {
    private val mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider)

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
//        val sideMargin = 48
//        val left = parent?.paddingLeft!! + sideMargin
//        val right: Int = parent?.width!! - parent?.paddingRight - sideMargin

//        val left = parent?.paddingStart!!
//        val right: Int = parent?.width!! - parent?.paddingEnd

        val childCount = parent!!.childCount
        for(i in 0..childCount-1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val left = params.marginStart
            val right: Int = child.right
            // 아이템의 바텀 위치 + 바텀 마진
            val top = child.bottom + params.bottomMargin
            // top + 디바이더 고유 높이
            val bottom = top + mDivider.intrinsicHeight

            // 좌표값 설정
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }
}
