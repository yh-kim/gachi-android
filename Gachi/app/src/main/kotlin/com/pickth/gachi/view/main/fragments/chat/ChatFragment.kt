package com.pickth.gachi.view.main.fragments.chat

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseFragment
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.main.fragments.chat.adapter.ChatAdapter
import kotlinx.android.synthetic.main.fragment_main_chat.view.*

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class ChatFragment: BaseFragment(), ChatContract.View {

    private lateinit var mPresenter: ChatPresenter
    private lateinit var mAdapter: ChatAdapter
    private lateinit var mRecyclerView: RecyclerView

    companion object {
        private val mInstance = ChatFragment()
        fun getInstance(): ChatFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main_chat, container, false)

        // adapter
        mAdapter = ChatAdapter()
        mRecyclerView = rootView.recycler_chat.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(MyDividerItemDecoration(context))
        }

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

    override fun clickAgain() {
        scrollToTop()
    }


    override fun scrollToTop() {
        if(mPresenter.getItemCount() < 1) return

        mRecyclerView.layoutManager.smoothScrollToPosition(mRecyclerView, RecyclerView.State(), 0)
//        mRecyclerView.layoutManager.scrollToPosition(0)
    }
}