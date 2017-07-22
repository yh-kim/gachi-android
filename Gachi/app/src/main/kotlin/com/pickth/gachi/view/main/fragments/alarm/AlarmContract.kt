package com.pickth.gachi.view.main.fragments.alarm

import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.view.main.fragments.alarm.adapter.AlarmAdapterContract

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
interface AlarmContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {
        fun setAlarmAdapterView(alarmView: AlarmAdapterContract.View)

        fun setAlarmAdapterModel(alarmModel: AlarmAdapterContract.Model)
    }
}