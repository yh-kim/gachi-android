package com.pickth.gachi.view.main

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.util.UserInfoManager

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
class MainPresenter(): MainContract.Presenter {

    lateinit private var mMainView: MainContract.View

    override fun attachView(view: BaseView<*>) {
        this.mMainView =  view as MainContract.View
    }

    override fun getUser(): UserInfoManager.User? = UserInfoManager.getUser(mMainView.getContext())
}