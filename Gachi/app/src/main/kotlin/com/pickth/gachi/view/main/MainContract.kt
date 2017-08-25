package com.pickth.gachi.view.main

import android.content.Context
import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.util.UserInfoManager

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
interface MainContract {
    interface View: BaseView<Presenter> {
        fun getContext(): Context
        fun changeFestivalAndSearch()
        fun isSearch(): Boolean
    }

    interface Presenter: BasePresenter {
        fun getUser(): UserInfoManager.User?
    }
}