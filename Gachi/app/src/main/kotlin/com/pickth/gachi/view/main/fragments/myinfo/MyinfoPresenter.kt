package com.pickth.gachi.view.main.fragments.myinfo

import android.content.Context
import android.view.View

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class MyinfoPresenter: MyinfoContract.Presenter {
    lateinit private var mView: View

    override fun attachView(view: View, context: Context) {
        mView = view
    }
}