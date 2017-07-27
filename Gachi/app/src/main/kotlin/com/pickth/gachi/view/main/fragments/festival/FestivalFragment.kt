package com.pickth.gachi.view.main.fragments.festival

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.commons.fragments.BaseFragment
import com.pickth.gachi.R

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class FestivalFragment: BaseFragment(), FestivalContract.View {
    private lateinit var mPresenter: FestivalPresenter

    companion object {
        private val mInstance = FestivalFragment()
        fun getInstance(): FestivalFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main_festival, container, false)

        mPresenter = FestivalPresenter()
        mPresenter.attachView(this)

        return rootView
    }

    override fun onResume() {
        super.onResume()
    }

}