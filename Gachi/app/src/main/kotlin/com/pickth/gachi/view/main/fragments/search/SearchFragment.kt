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

package com.pickth.gachi.view.main.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.pickth.gachi.R
import com.pickth.gachi.adapter.pager.MainPagerModel
import com.pickth.gachi.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_search.view.*
import org.jetbrains.anko.toast

class SearchFragment: BaseFragment(), SearchContract.View {

    private lateinit var mAdapter: MainPagerModel
    private lateinit var mPresenter: SearchPresenter

    companion object {
        private val mInstance = SearchFragment()
        fun getInstance(): SearchFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main_search, container, false)

        mPresenter = SearchPresenter().apply {
            attachView(this@SearchFragment)
        }

        rootView.et_search_festival.setOnEditorActionListener { textView, i, keyEvent ->
            when(i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    activity.toast("search ${textView.text.toString().trim()}")
                    true
                }
            }

            false
        }

        return rootView
    }

    fun setMainPagerAdapter(adapter: MainPagerModel) {
        mAdapter = adapter
    }

    override fun clickAgain() {
//        if(mAdapter != null) mAdapter.changeBetweenFragment()
    }
}