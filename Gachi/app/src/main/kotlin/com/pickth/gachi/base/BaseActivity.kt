package com.pickth.gachi.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by yonghoon on 2017-07-09.
 * Blog   : http://blog.pickth.com
 * Github : https://github.com/yh-kim
 * Mail   : yonghoon.kim@pickth.com
 */
abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}