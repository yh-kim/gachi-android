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

    constructor(context: Context): super(context) {
        initializeView()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initializeView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initializeView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        initializeView()
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

    fun notifyDataSetChanged() {
        for(i in 0..mReliability/20-1) mCircleViews[i].isSelected = true
    }

    private fun getAttrs(attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReliabilityBar, defStyleAttr, defStyleRes)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
//        int bg_resID = typedArray.getResourceId(R.styleable.LoginButton_bg, R.drawable.login_naver_bg);
//        bg.setBackgroundResource(bg_resID);
//
//        int symbol_resID = typedArray.getResourceId(R.styleable.LoginButton_symbol, R.drawable.login_naver_symbol);
//        symbol.setImageResource(symbol_resID);
//
//        int textColor = typedArray.getColor(R.styleable.LoginButton_textColor, 0);
//        text.setTextColor(textColor);
//
//        String text_string = typedArray.getString(R.styleable.LoginButton_text);
//        text.setText(text_string);

        typedArray.recycle();

    }
}