package com.pickth.gachi.view.login

import android.os.Bundle
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.view.main.MainActivity
import com.pickth.gachi.view.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fl_login_with_email.setOnClickListener {
        }


        tv_signup.setOnClickListener {
            startActivity<SignupActivity>()
            finish()
        }

        tv_skip.setOnClickListener {
            startActivity<MainActivity>()
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy();
    }

    private fun getUser() {
        startActivity<MainActivity>()
        finish()
    }


}

