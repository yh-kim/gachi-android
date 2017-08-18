package com.pickth.gachi.view.main.fragments.myinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pickth.gachi.R
import com.pickth.gachi.view.main.fragments.TapBaseFragment
import kotlinx.android.synthetic.main.fragment_main_myinfo.view.*

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class MyinfoFragment : TapBaseFragment(), MyinfoContract.View {

    private lateinit var mPresenter: MyinfoPresenter
    private lateinit var rootView: View

    companion object {
        private val mInstance = MyinfoFragment()
        fun getInstance(): MyinfoFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_main_myinfo, container, false)

        mPresenter = MyinfoPresenter()
        mPresenter.attachView(this)

        Glide.with(rootView.context)
                .load(R.drawable.test)
                .apply(RequestOptions().circleCrop())
                .into(rootView.iv_myinfo_photo)
        rootView.tv_myinfo_name.text = "test"

        setMyReliability(86)

        return rootView
    }

    override fun setMyReliability(reliability: Int) {
        rootView.rv_myinfo_reliability.setReliability(reliability)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun clickAgain() {
    }
}