package com.pickth.gachi.view.main.fragments.chat

import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
interface ChatContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}