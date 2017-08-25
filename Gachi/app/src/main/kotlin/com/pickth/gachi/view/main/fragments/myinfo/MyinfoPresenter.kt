package com.pickth.gachi.view.main.fragments.myinfo

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.util.UserInfoManager

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class MyinfoPresenter: MyinfoContract.Presenter {

    lateinit private var mView: MyinfoContract.View

    override fun attachView(view: BaseView<*>) {
        this.mView = view as MyinfoContract.View

        val user = getUser()
        if(user == null) {
            // guest
            mView.initializeGuestLayout()
        } else {
            // user
            mView.initializeUserLayout(user)
        }
    }

    override fun getUser(): UserInfoManager.User? = UserInfoManager.getUser(mView.getContext())
}