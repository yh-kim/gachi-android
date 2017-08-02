package com.pickth.gachi.view.main.fragments.alarm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseFragment
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.alarm.AlarmDetailActivity
import com.pickth.gachi.view.main.fragments.alarm.adapter.AlarmAdapter
import kotlinx.android.synthetic.main.fragment_main_alarm.view.*
import org.jetbrains.anko.startActivity

class AlarmFragment: BaseFragment(), AlarmContract.View {

    private lateinit var mPresenter: AlarmPresenter
    private lateinit var mAdapter: AlarmAdapter
    private lateinit var mRecyclerView: RecyclerView

    companion object {
        private val mInstance = AlarmFragment()
        fun getInstance(): AlarmFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main_alarm, container, false)

        // adapter
        mAdapter = AlarmAdapter()
        mRecyclerView = rootView.recycler_alarm.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(MyDividerItemDecoration(context))
        }

        // presenter
        mPresenter = AlarmPresenter().apply {
            attachView(this@AlarmFragment)
            setAlarmAdapterView(mAdapter)
            setAlarmAdapterModel(mAdapter)
        }

        // test
        mPresenter.addTest()

        return rootView
    }

    override fun onResume() {
        super.onResume()
    }

    override fun intentToAlarmDetailActivity(position: Int) {
        activity.startActivity<AlarmDetailActivity>()
    }

    override fun clickAgain() {
        scrollToTop()
    }

    override fun scrollToTop() {
        if(mPresenter.getItemCount() < 1) return

        mRecyclerView.smoothScrollToPosition(0)
    }
}