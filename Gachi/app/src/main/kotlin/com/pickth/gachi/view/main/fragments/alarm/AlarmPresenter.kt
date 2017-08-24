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
    }

    override fun getItemCount(): Int = mAlarmModel.getItemCount()

    // TODO: Remove test case
    fun addTest() {
        mAlarmModel.addItem(Alarm("김도희님께서\n 펜타포트 같이 갈 사람 괌… 방에서\n 참여대기 중입니다.", 1))
        mAlarmModel.addItem(Alarm("백예진님께서\n 펜타포트 같이 갈 사람 괌… 방에서\n 참여수락을 하셨습니다.", 0))
    }
}