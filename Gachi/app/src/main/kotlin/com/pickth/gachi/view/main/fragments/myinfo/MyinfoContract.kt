package com.pickth.gachi.view.main.fragments.myinfo

import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
interface MyinfoContract {
    interface View: BaseView<Presenter> {
        fun setMyReliability(reliability: Int)
    }

    interface Presenter: BasePresenter {
    }
}