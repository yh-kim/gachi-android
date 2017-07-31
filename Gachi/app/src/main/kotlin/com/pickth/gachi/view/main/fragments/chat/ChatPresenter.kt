package com.pickth.gachi.view.main.fragments.chat

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.view.main.fragments.chat.adapter.Chat
import com.pickth.gachi.view.main.fragments.chat.adapter.ChatAdapterContract

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class ChatPresenter: ChatContract.Presenter {

    lateinit private var mView: ChatContract.View
    lateinit private var mChatView: ChatAdapterContract.View
    lateinit private var mChatModel: ChatAdapterContract.Model

    override fun attachView(view: BaseView<*>) {
        this.mView = view as ChatContract.View
    }

    override fun setChatAdapterView(chatView: ChatAdapterContract.View) {
        mChatView = chatView
    }

    override fun setChatAdapterModel(chatModel: ChatAdapterContract.Model) {
        mChatModel = chatModel
    }

    override fun getItemCount(): Int = mChatModel.getItemCount()

    fun addTest() {
        for(i in 0..10) {
            mChatModel.addItem(Chat("${i}번째 대화방입니다."))
        }
    }
}