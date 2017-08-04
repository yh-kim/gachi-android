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
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.pickth.gachi.R
import com.pickth.gachi.adapter.pager.MainPagerModel
import com.pickth.gachi.dialog.GachiProgressDialog
import com.pickth.gachi.util.GridSpacingItemDecoration
import com.pickth.gachi.view.main.fragments.TapBaseFragment
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival
import com.pickth.gachi.view.main.fragments.search.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_main_search.view.*

class SearchFragment : TapBaseFragment(), SearchContract.View {

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

        val adapter = SearchAdapter()
        rootView.rv_search_festival.run {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = adapter
            addItemDecoration(GridSpacingItemDecoration(context,2, 16, false))
        }

        // test input
        adapter.addItem(Festival("07.27 ~ 07.29", "0", "2012 지산 밸리 록 페스티벌"))
        adapter.addItem(Festival("08.11 ~ 08.13", "1", "2017 인천 펜사포트 락 페스티벌"))
        adapter.addItem(Festival("08.11 ~ 08.13", "2", "2017 인천 펜사포트 락 페스티벌"))
        adapter.addItem(Festival("07.29 ~ 07.31", "3", "2011 지산 밸리 록 페스티벌"))
        adapter.addItem(Festival("09.14 ~ 09.15", "4", "렛츠락 페스티벌"))

        rootView.et_search_festival.setOnEditorActionListener { textView, i, keyEvent ->
            when(i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    val dialog = GachiProgressDialog(context).showDialog()
                    Handler().postDelayed({
                        dialog.dismiss()
                        rootView.ll_search_result_screen.visibility = View.VISIBLE
                    }, 2000)
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