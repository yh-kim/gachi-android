package com.pickth.gachi.view.main

import android.os.Bundle
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity

/**
 * Created by yonghoon on 2017-07-09.
 * Blog   : http://blog.pickth.com
 * Github : https://github.com/yh-kim
 * Mail   : yonghoon.kim@pickth.com
 */
class MainActivity : BaseActivity(), MainContract.View {

    private lateinit var mMainPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mMainPresenter = presenter
    }
}
