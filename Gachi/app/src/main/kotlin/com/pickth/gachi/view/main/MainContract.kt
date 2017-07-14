package com.pickth.gachi.view.main

import com.pickth.gachi.base.BasePresenter
import com.pickth.gachi.base.BaseView

/**
 * Created by yonghoon on 2017-07-09.
 * Blog   : http://blog.pickth.com
 * Github : https://github.com/yh-kim
 * Mail   : yonghoon.kim@pickth.com
 */
interface MainContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}