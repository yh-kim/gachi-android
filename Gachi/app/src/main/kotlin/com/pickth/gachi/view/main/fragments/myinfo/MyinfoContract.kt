package com.pickth.gachi.view.main.fragments.myinfo

import android.content.Context
import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.util.UserInfoManager

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
interface MyinfoContract {
    interface View: BaseView<Presenter> {
        fun getContext(): Context
        fun initializeUserLayout(user: UserInfoManager.User)
        fun initializeGuestLayout()
        fun setMyReliability(reliability: Int)
    }

    interface Presenter: BasePresenter {
        fun getUser(): UserInfoManager.User?
    }
}