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
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.pickth.commons.extensions.hideKeyboard
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.util.GridSpacingItemDecoration
import com.pickth.gachi.view.chat.adapter.ChatDetailAdapter
import kotlinx.android.synthetic.main.activity_chat_detail.*
import org.jetbrains.anko.toast

class ChatDetailActivity: BaseActivity(), ChatDetailContract.View {

    private lateinit var mPresenter: ChatDetailPresenter
    private lateinit var mAdapter: ChatDetailAdapter
    private lateinit var mParticipantAdapter: ParticipantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)

//        val icon = AppCompatResources.getDrawable(this, R.drawable.ic_back)!!
//        DrawableCompat.setTint(icon, ContextCompat.getColor(this, R.color.colorWhite))

        // actionbar
        setSupportActionBar(chat_toolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

//        title = resources.getStringArray(R.array.page_title)[0]

        // adpater
        mAdapter = ChatDetailAdapter()

        rv_chat_detail.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }

        mParticipantAdapter = ParticipantAdapter()

        rv_chat_participant_list.run {
            layoutManager = GridLayoutManager(context, 6)
            this.adapter = mParticipantAdapter
            addItemDecoration(GridSpacingItemDecoration(context,6, 12, false))
        }

        // presenter
        mPresenter = ChatDetailPresenter().apply {
            attachView(this@ChatDetailActivity)
            setChatDetailAdapterView(mAdapter)
            setChatDetailAdapterModel(mAdapter)
            setParticipantAdapter(mParticipantAdapter)
            getParticipant()
        }

        btn_chat_detail_send.setOnClickListener {
            var msg = et_chat_detail_msg.text.toString().trim()

            if(msg.length < 1) {
                toast("텍스트를 입력해주세요.")
                return@setOnClickListener
            }

            et_chat_detail_msg.text = null
            mPresenter.sendMessage(msg)
        }

        tv_chat_participant.setOnClickListener {
            tv_chat_participant.visibility = View.GONE
            tv_chat_show.visibility = View.VISIBLE

            rv_chat_participant_list.run {
//                clearAnimation()
                visibility = View.VISIBLE

//                setShowVerticalTranslateAnimation(2000)
            }
        }

        tv_chat_show.setOnClickListener {
            tv_chat_show.visibility = View.GONE
            tv_chat_participant.visibility = View.VISIBLE

            rv_chat_participant_list.run {
                visibility = View.GONE
            }
        }

    }

    override fun getLid(): String = intent.getStringExtra("lid")

    override fun scrollToPosition(position: Int) {
        if(mPresenter.getItemCount() < 0) return

        rv_chat_detail.smoothScrollToPosition(position)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                hideKeyboard()
                finish()
            }

            R.id.chat_menu_add -> {
                // add
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}