package com.pickth.gachi.view.main.fragments.alarm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class AlarmFragment: Fragment(), AlarmContract.View {

    private lateinit var mPresenter: AlarmContract.Presenter

    companion object {
        fun newInstance(): AlarmFragment = AlarmFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_alarm, container, false)

        return root
    }

    override fun onResume() {
        super.onResume()
//        mPresenter.start()
    }
}