package com.pickth.gachi.view.main.fragments.myinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.pickth.gachi.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_myinfo.view.*

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class MyinfoFragment: Fragment(), MyinfoContract.View {

    private lateinit var mPresenter: MyinfoPresenter
    private lateinit var rootView: View

    companion object {
        fun newInstance(): MyinfoFragment = MyinfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_myinfo, container, false)

        mPresenter = MyinfoPresenter()
        mPresenter.attachView(this)

        UserManagement.requestMe(object: MeResponseCallback() {
            override fun onSessionClosed(errorResult: ErrorResult?) {
            }

            override fun onNotSignedUp() {
            }

            override fun onSuccess(result: UserProfile?) {

                Picasso.with(rootView.context)
                        .load(result?.profileImagePath)
                        .into(rootView.iv_myinfo_photo)
                rootView.tv_myinfo_name.text = result?.nickname

            }

        })

        setMyReliability(86)

        return rootView
    }

    override fun setMyReliability(reliability: Int) {
        rootView.rv_myinfo_reliability.setReliability(reliability)
    }

    override fun onResume() {
        super.onResume()
    }
}