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

package com.pickth.gachi.view.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.view.chat.adapter.ChatDetailAdapter
import kotlinx.android.synthetic.main.activity_chat_detail.*
import org.jetbrains.anko.toast

class ChatDetailActivity: BaseActivity(), ChatDetailContract.View {

    private lateinit var mPresenter: ChatDetailPresenter
    private lateinit var mAdapter: ChatDetailAdapter
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)

        mRecyclerView = rv_chat_detail

        // adpater
        mAdapter = ChatDetailAdapter()

        rv_chat_detail.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }

        // presenter
        mPresenter = ChatDetailPresenter().apply {
            attachView(this@ChatDetailActivity)
            setChatDetailAdapterView(mAdapter)
            setChatDetailAdapterModel(mAdapter)
        }

        iv_chat_detail_send.setOnClickListener {
            var msg = et_chat_detail_msg.text.toString().trim()

            if(msg.length < 1) {
                toast("텍스트를 입력해주세요.")
                return@setOnClickListener
            }

            et_chat_detail_msg.text = null
            mPresenter.sendMessage(msg)
        }

    }

    override fun scrollToPosition(position: Int) {
        if(mPresenter.getItemCount() < 0) return

        mRecyclerView.smoothScrollToPosition(position)
    }
}