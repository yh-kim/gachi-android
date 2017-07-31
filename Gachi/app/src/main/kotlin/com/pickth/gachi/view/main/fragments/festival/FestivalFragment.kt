package com.pickth.gachi.view.main.fragments.festival

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseFragment
import org.jetbrains.anko.toast

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

    override fun clickAgain() {
        activity.toast("또 눌렀음")
    }

}