package com.pickth.gachi.view.splash

import android.os.Bundle
import android.os.Handler
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.pickth.commons.activities.BaseActivity
import com.pickth.commons.extensions.intent
import com.pickth.commons.extensions.toast
import com.pickth.gachi.R
import com.pickth.gachi.view.login.LoginActivity
import com.pickth.gachi.view.main.MainActivity

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
        UserManagement.requestMe(object: MeResponseCallback() {
            override fun onSuccess(result: UserProfile?) {
                toast("로그인 성공")
                intent(MainActivity::class.java)
                finish()
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                toast("세션이 닫힘, 로그인 페이지로 이동")
                intent(LoginActivity::class.java)
                finish()
            }

            override fun onNotSignedUp() {
                toast("세션은 있으나 회원가입이 안됨, 회원가입 페이지로 이동")
            }

            override fun onFailure(errorResult: ErrorResult?) {
                super.onFailure(errorResult)
                toast("요청이 실패, 로그인 페이지로 이동")
                intent(LoginActivity::class.java)
                finish()
            }
        })
    }

    private val mTimer = Runnable {
        getUser()
    }
}
