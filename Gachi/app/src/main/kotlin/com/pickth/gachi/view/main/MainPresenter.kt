package com.pickth.gachi.view.main

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
class MainPresenter(mainView: MainContract.View): MainContract.Presenter {

    private var mMainView: MainContract.View = mainView

    init {
        mMainView.setPresenter(this)
    }

    override fun start() {
    }
}