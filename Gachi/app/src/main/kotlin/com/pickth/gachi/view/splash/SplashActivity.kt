package com.pickth.gachi.view.splash

import android.os.Bundle
import android.os.Handler
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.view.login.LoginActivity
import com.pickth.gachi.view.main.MainActivity
import org.jetbrains.anko.startActivity

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 0.8 sec
        Handler().postDelayed(mTimer, 8*100)
    }

    private fun getUser() {
        startActivity<MainActivity>()
        finish()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

    private val mTimer = Runnable {
//        getUser()
        startActivity<LoginActivity>()
        finish()
    }
}
