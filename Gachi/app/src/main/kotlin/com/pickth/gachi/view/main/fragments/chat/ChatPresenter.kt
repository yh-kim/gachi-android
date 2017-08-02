package com.pickth.gachi.view.main.fragments.chat

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.main.fragments.chat.adapter.Chat
import com.pickth.gachi.view.main.fragments.chat.adapter.ChatAdapterContract

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class ChatPresenter: ChatContract.Presenter, OnItemClickListener {

    lateinit private var mView: ChatContract.View
    lateinit private var mChatView: ChatAdapterContract.View
    lateinit private var mChatModel: ChatAdapterContract.Model

    override fun attachView(view: BaseView<*>) {
        this.mView = view as ChatContract.View
    }

    override fun setChatAdapterView(chatView: ChatAdapterContract.View) {
        mChatView = chatView
        mChatView.setItemClickListener(this)
    }

    override fun setChatAdapterModel(chatModel: ChatAdapterContract.Model) {
        mChatModel = chatModel
    }

    override fun getItemCount(): Int = mChatModel.getItemCount()

    override fun onItemClick(position: Int) {
        mView.intentToChatDetailActivity(position)
    }

    fun addTest() {
        for(i in 0..10) {
            mChatModel.addItem(Chat("${i}번째 대화방입니다."))
        }
    }
}