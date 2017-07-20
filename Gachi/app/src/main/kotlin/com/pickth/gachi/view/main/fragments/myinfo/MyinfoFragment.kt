package com.pickth.gachi.view.main.fragments.myinfo

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
class MyinfoFragment: Fragment(), MyinfoContract.View {
    private lateinit var mPresenter: MyinfoContract.Presenter

    override fun setPresenter(presenter: MyinfoContract.Presenter) {
        mPresenter = presenter
    }

    companion object {
        fun newInstance(): MyinfoFragment = MyinfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_myinfo, container, false)

        return root
    }

    override fun onResume() {
        super.onResume()
//        mPresenter.start()
    }
}