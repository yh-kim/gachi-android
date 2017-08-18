package com.pickth.gachi.base

import android.app.Application

/**
 * Created by yonghoon on 2017-07-11.
 * Mail   : yonghoon.kim@pickth.com
 */
class BaseApplication: Application() {


    companion object {

    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onTerminate() {
        super.onTerminate()
    }
}