package com.pickth.gachi.view.main

import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
interface MainContract {
    interface View: BaseView<Presenter> {
        fun changeFestivalAndSearch()
        fun isSearch(): Boolean
    }

    interface Presenter: BasePresenter {

    }
}