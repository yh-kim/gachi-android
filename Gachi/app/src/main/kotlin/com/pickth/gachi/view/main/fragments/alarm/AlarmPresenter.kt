package com.pickth.gachi.view.main.fragments.alarm

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.main.fragments.alarm.adapter.Alarm
import com.pickth.gachi.view.main.fragments.alarm.adapter.AlarmAdapterContract

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class AlarmPresenter: AlarmContract.Presenter, OnItemClickListener {

    lateinit private var mView: AlarmContract.View
    lateinit private var mAlarmView: AlarmAdapterContract.View
    lateinit private var mAlarmModel: AlarmAdapterContract.Model


    override fun attachView(view: BaseView<*>) {
        this.mView = view as AlarmContract.View
    }

    override fun setAlarmAdapterView(alarmView: AlarmAdapterContract.View) {
        this.mAlarmView = alarmView

        this.mAlarmView.setOnClickListener(this)
    }

    override fun setAlarmAdapterModel(alarmModel: AlarmAdapterContract.Model) {
        this.mAlarmModel = alarmModel
    }

    override fun onItemClick(position: Int) {
        mView.intentToAlarmDetailActivity(position)
    }

    override fun getItemCount(): Int = mAlarmModel.getItemCount()

    // TODO: Remove test case
    fun addTest() {
        for(i in 0..0) {
            mAlarmModel.addItem(Alarm("알림$i"))
        }
    }
}