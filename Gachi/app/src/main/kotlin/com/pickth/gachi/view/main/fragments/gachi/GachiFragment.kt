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

package com.pickth.gachi.view.main.fragments.gachi

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.commons.fragments.BaseFragment
import com.pickth.gachi.R
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.main.fragments.chat.ChatFragment
import com.pickth.gachi.view.main.fragments.gachi.adapter.GachiAdapter
import kotlinx.android.synthetic.main.fragment_gachi.view.*

class GachiFragment: BaseFragment(), GachiContract.View {

    private lateinit var mPresenter: GachiPresenter
    private lateinit var mAdapter: GachiAdapter

    companion object {
        private val mInstance = GachiFragment()
        fun getInstance(): GachiFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_gachi, container, false)

        // adapter
        mAdapter = GachiAdapter()
        with(rootView.recycler_gachi) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(MyDividerItemDecoration(context))
        }

        // presenter
        mPresenter = GachiPresenter()
        mPresenter.attachView(this)
        with(mPresenter) {
            setGachiAdapterView(mAdapter)
            setGachiAdapterModel(mAdapter)
        }

        // test
        mPresenter.addTest()

        return rootView
    }

    override fun onResume() {
        super.onResume()
    }
}