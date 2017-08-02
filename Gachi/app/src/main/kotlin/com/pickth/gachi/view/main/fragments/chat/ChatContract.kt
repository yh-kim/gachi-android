package com.pickth.gachi.view.main.fragments.chat

import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.view.main.fragments.chat.adapter.ChatAdapterContract

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
interface ChatContract {
    interface View: BaseView<Presenter> {
        fun intentToChatDetailActivity(position: Int)

        fun scrollToTop()
    }

    interface Presenter: BasePresenter {
        fun setChatAdapterView(chatView: ChatAdapterContract.View)

        fun setChatAdapterModel(chatModel: ChatAdapterContract.Model)

        fun getItemCount(): Int
    }
}