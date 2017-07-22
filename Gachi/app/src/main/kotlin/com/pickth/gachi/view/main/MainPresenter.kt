package com.pickth.gachi.view.main

import android.content.Context
import android.view.View

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
class MainPresenter(mainView: MainContract.View): MainContract.Presenter {

    private var mMainView: MainContract.View = mainView

    override fun attachView(view: View, context: Context) {
    }
}