package com.pickth.gachi.view.main.fragments.alarm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.main.fragments.alarm.adapter.AlarmAdapter
import kotlinx.android.synthetic.main.fragment_alarm.view.*

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class AlarmFragment: Fragment(), AlarmContract.View {

    private lateinit var mPresenter: AlarmPresenter
    private lateinit var mAdapter: AlarmAdapter

    companion object {
        fun newInstance(): AlarmFragment = AlarmFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_alarm, container, false)

        // adapter
        mAdapter = AlarmAdapter()
        rootView.recycler_alarm.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rootView.recycler_alarm.adapter = mAdapter
        rootView.recycler_alarm.addItemDecoration(MyDividerItemDecoration(context))

        // presenter
        mPresenter = AlarmPresenter()
        mPresenter.attachView(rootView, context)
        mPresenter.setAlarmAdapterView(mAdapter)
        mPresenter.setAlarmAdapterModel(mAdapter)

        // test
        mPresenter.addTest()

        return rootView
    }

    override fun onResume() {
        super.onResume()
    }
}