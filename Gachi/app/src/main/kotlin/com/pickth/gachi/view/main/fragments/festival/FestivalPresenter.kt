package com.pickth.gachi.view.main.fragments.festival

import com.pickth.commons.mvp.BaseView

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class FestivalPresenter: FestivalContract.Presenter {

    lateinit private var mView: FestivalContract.View

    override fun attachView(view: BaseView<*>) {
        this.mView = view as FestivalContract.View
    }
}