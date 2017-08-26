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
import android.util.Log
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
import org.json.JSONObject
import java.text.SimpleDateFormat

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
            getGachiInfo()
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
                visibility = View.VISIBLE
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

    override fun bindGachiInfo(responseBody: String) {
        val json = JSONObject(responseBody)
        Log.d(TAG, "getGachiInfo onResponse, json: ${json}")

        // get gachi info
        var title = json.getString("detail")
        var startDate = json.getString("lead_from").split("T")[0].replace("-",".").trim()
        var dday = ((System.currentTimeMillis() - SimpleDateFormat("yyyy.MM.dd").parse(startDate).time) / (1000 * 60 * 60 * 24)).toInt()

        // get participant list
        var members = json.getJSONArray("member")
        for(i in 0..members.length() - 1) {
            var member = members.getJSONObject(i)

            var memberUid = member.getString("uid")
            var memberNickname = member.getString("nickname")
            var memberProfile = member.getString("profile_image")

            // bind participant
            mParticipantAdapter.addItem(Participant(memberUid, memberNickname, memberProfile))
        }

        // bind view
        tv_chat_detail_title.text = title
        tv_chat_detail_dday.text = "D - ${if(dday == 0) "day" else dday}"
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