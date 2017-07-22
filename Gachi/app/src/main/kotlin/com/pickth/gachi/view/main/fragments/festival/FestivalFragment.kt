package com.pickth.gachi.view.main.fragments.festival

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
class FestivalFragment: Fragment(), FestivalContract.View {

    private lateinit var mPresenter: FestivalContract.Presenter

    companion object {
        fun newInstance(): FestivalFragment = FestivalFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_festival, container, false)

        return root
    }

    override fun onResume() {
        super.onResume()
//        mPresenter.start()
    }

}