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
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseFragment
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.gachi.GachiDetailActivity
import com.pickth.gachi.view.main.fragments.gachi.adapter.GachiAdapter
import kotlinx.android.synthetic.main.fragment_main_gachi.view.*
import org.jetbrains.anko.startActivity

class GachiFragment: BaseFragment(), GachiContract.View {

    private lateinit var mPresenter: GachiPresenter
    private lateinit var mAdapter: GachiAdapter
    private lateinit var mRecyclerView: RecyclerView

    companion object {
        private val mInstance = GachiFragment()
        fun getInstance(): GachiFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main_gachi, container, false)

        // adapter
        mAdapter = GachiAdapter()
        mRecyclerView = rootView.recycler_gachi.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(MyDividerItemDecoration(context))
        }

        // presenter
        mPresenter = GachiPresenter().apply {
            attachView(this@GachiFragment)
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

    override fun intentToGachiDetailActivity(position: Int) {
        activity.startActivity<GachiDetailActivity>()
    }

    override fun clickAgain() {
        scrollToTop()
    }

    override fun scrollToTop() {
        if(mPresenter.getItemCount() < 1) return

        mRecyclerView.smoothScrollToPosition(0)
    }
}