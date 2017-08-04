package com.pickth.gachi.view.main.fragments.chat

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.view.main.fragments.TapBaseFragment
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.chat.ChatDetailActivity
import com.pickth.gachi.view.main.fragments.chat.adapter.ChatAdapter
import kotlinx.android.synthetic.main.fragment_main_chat.view.*
import org.jetbrains.anko.startActivity

class ChatFragment : TapBaseFragment(), ChatContract.View {

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
        mPresenter = ChatPresenter().apply {
            attachView(this@ChatFragment)
            setChatAdapterView(mAdapter)
            setChatAdapterModel(mAdapter)
        }

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

    override fun intentToChatDetailActivity(position: Int) {
        activity.startActivity<ChatDetailActivity>()
    }

    override fun scrollToTop() {
        if(mPresenter.getItemCount() < 1) return

        mRecyclerView.smoothScrollToPosition(0)
    }
}