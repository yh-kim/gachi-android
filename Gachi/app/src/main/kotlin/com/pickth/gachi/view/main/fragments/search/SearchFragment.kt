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
import com.pickth.commons.fragments.BaseFragment
import com.pickth.gachi.R
import com.pickth.gachi.view.main.fragments.festival.FestivalFragment
import kotlinx.android.synthetic.main.fragment_main_search.view.*

class SearchFragment: BaseFragment() {

    companion object {
        private val mInstance = SearchFragment()
        fun getInstance(): SearchFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main_search, container, false)

        rootView.tv_search_test.setOnClickListener {
            fragmentManager.beginTransaction().replace(container!!.id, FestivalFragment.getInstance()).commit()
        }

        return rootView
    }
}