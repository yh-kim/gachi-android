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

package com.pickth.gachi.view.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.pickth.gachi.R
import kotlinx.android.synthetic.main.view_reliability.view.*

class ReliabilityView : LinearLayout {

    private var mReliability = 68
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private val mCircleViews = ArrayList<ImageView>()

    constructor(context: Context): this(context, null, 0) {
    }

    constructor(context: Context, attrs: AttributeSet): this(context, attrs, 0) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initializeView()
        getAttrs(attrs, defStyleAttr)
    }

    private fun initializeView() {
        val infService = Context.LAYOUT_INFLATER_SERVICE
        var mLayoutInflater = context.getSystemService(infService) as LayoutInflater

        var rootView = mLayoutInflater.inflate(R.layout.view_reliability, this, false)
        addView(rootView)

        with(rootView) {
            mCircleViews.add(iv_reliability_circle_1)
            mCircleViews.add(iv_reliability_circle_2)
            mCircleViews.add(iv_reliability_circle_3)
            mCircleViews.add(iv_reliability_circle_4)
            mCircleViews.add(iv_reliability_circle_5)
        }

        notifyDataSetChanged()
    }

    /**
     * @param size width, height pixel
     */
    fun setViewSize(size: Int) {
        val param = LinearLayout.LayoutParams(size, size)
        val margin = size/10
        param.setMargins(margin,0,margin,0)
        for(i in mCircleViews) {
            i.layoutParams = param
        }
    }

    fun notifyDataSetChanged() {
        for(i in 0..mReliability/20-1) mCircleViews[i].isSelected = true
    }

    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReliabilityBar, defStyleAttr, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val size = typedArray.getInt(R.styleable.ReliabilityBar_size, 60)
        setViewSize(size)

        typedArray.recycle();
    }
}