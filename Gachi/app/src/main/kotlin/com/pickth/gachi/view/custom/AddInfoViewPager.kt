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
import android.support.v4.app.FragmentManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseAddInfoFragment
import com.pickth.gachi.extensions.convertDpToPixel
import kotlinx.android.synthetic.main.view_pager_add_info.view.*

class AddInfoViewPager : LinearLayout {
    private var mFrameLayout: FrameLayout? = null
    private var mFragmentManager: FragmentManager? = null
    private val mIndexButtons = ArrayList<ImageView>()
    private val mViews = ArrayList<BaseAddInfoFragment>()
    var currentIndex = 0

    companion object {
        private val ITEM_COUNT = 5
    }

    constructor(context: Context): super(context) {
        initializeView()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initializeView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initializeView()
    }

    fun setFragmentManager(fragmentManager: FragmentManager) {
        mFragmentManager = fragmentManager
    }

    fun addItemView(fragment: BaseAddInfoFragment) {
        fragment.setChangeFragmentListener(object: ChangeFragmentListener {
            override fun onChange() {
                changeNextFragment()
            }

        })
        mViews.add(fragment)
    }

    fun getFragments(position: Int) = mViews[position]

    private fun initializeView() {
        var mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = mLayoutInflater.inflate(R.layout.view_pager_add_info, this, false)

        mFrameLayout = rootView.fl_view_pager

        val size = rootView.context.convertDpToPixel(5)
        var params = LinearLayout.LayoutParams(size, size)
        params.leftMargin = rootView.context.convertDpToPixel(2)
        params.rightMargin = rootView.context.convertDpToPixel(2)
        params.topMargin = rootView.context.convertDpToPixel(39)
        params.bottomMargin = rootView.context.convertDpToPixel(50)


        for(i in 0..ITEM_COUNT-1) {
            mIndexButtons.add(ImageView(context))

            mIndexButtons[i].run {
                setImageResource(R.drawable.selector_reliability_circle)
                isSelected = (i == currentIndex)
//                setPadding(10, 10, 10, 10)
            }

            rootView.ll_view_pager_buttons.addView(mIndexButtons[i], params)
        }

        addView(rootView)
    }

    fun changeNextFragment() {
        currentIndex++

        if(mFragmentManager != null) {
            mFragmentManager?.beginTransaction()?.run {
                setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                replace(mFrameLayout!!.id, mViews[currentIndex])?.commit()
            }
        }

        updateIndexes()
    }

    fun changePreFragment() {
        if(currentIndex == 0) return

        currentIndex--

        if(mFragmentManager != null) {
            mFragmentManager?.beginTransaction()?.run {
                setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                replace(mFrameLayout!!.id, mViews[currentIndex])?.commit()
            }
        }

        updateIndexes()
    }

    fun updateIndexes() {
        for(i in 0..ITEM_COUNT-1) {
            mIndexButtons[i].isSelected = (i == currentIndex)
        }
    }

    fun notifyDataSetChanged() {
        if(mFragmentManager != null) {
            mFragmentManager?.beginTransaction()?.replace(R.id.fl_view_pager, mViews[currentIndex])?.commit()
        }

        updateIndexes()
    }

    interface ChangeFragmentListener {
        fun onChange()
    }
}

