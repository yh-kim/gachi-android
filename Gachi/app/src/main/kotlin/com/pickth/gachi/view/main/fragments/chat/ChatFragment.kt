package com.pickth.gachi.view.main.fragments.chat

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.main.fragments.chat.adapter.ChatAdapter
import kotlinx.android.synthetic.main.fragment_chat.view.*

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class ChatFragment: Fragment(), ChatContract.View {

    private lateinit var mPresenter: ChatPresenter
    private lateinit var mAdapter: ChatAdapter

    companion object {
        fun newInstance(): ChatFragment = ChatFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_chat, container, false)

        // adapter
        mAdapter = ChatAdapter()
        rootView.recycler_chat.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rootView.recycler_chat.adapter = mAdapter
        rootView.recycler_chat.addItemDecoration(MyDividerItemDecoration(context))

        // presenter
        mPresenter = ChatPresenter()
        mPresenter.attachView(this)
        mPresenter.setChatAdapterView(mAdapter)
        mPresenter.setChatAdapterModel(mAdapter)

        // test
        mPresenter.addTest()

        return rootView
    }

    override fun onResume() {
        super.onResume()
    }
}