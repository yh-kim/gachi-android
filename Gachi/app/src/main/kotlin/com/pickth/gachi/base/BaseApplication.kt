package com.pickth.gachi.base

import android.app.Application
import android.util.Log

/**
 * Created by yonghoon on 2017-07-11.
 * Mail   : yonghoon.kim@pickth.com
 */
class BaseApplication: Application() {


    companion object {
        val TAG = "GACHI__BaseApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}