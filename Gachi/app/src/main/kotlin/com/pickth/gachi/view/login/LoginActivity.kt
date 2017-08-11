package com.pickth.gachi.view.login

import android.os.Bundle
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.view.main.MainActivity
import com.pickth.gachi.view.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
class LoginActivity : BaseActivity() {

    // kakao login
    private var mSessionCallback = object: ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            setContentView(R.layout.activity_login)
        }

        override fun onSessionOpened() {
            getUser()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fl_login_with_email.setOnClickListener {
        }

        fl_login_with_kakao.setOnClickListener {
            kakao_sign_in_button.performClick()
        }

        tv_signup.setOnClickListener {
            startActivity<SignupActivity>()
            finish()
        }

        tv_skip.setOnClickListener {
            startActivity<MainActivity>()
            finish()
        }

        // kakao login
        Session.getCurrentSession().addCallback(mSessionCallback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    override fun onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }

    private fun getUser() {
        UserManagement.requestMe(object: MeResponseCallback() {
            override fun onSuccess(result: UserProfile?) {
                toast("로그인 성공")
//                startActivity<MainActivity>()
//                finish()
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                toast("세션이 닫힘, 로그인 페이지로 이동")
            }

            override fun onNotSignedUp() {
                toast("세션은 있으나 회원가입이 안됨, 회원가입 페이지로 이동")
            }

            override fun onFailure(errorResult: ErrorResult?) {
                super.onFailure(errorResult)
                toast("요청이 실패, 로그인 페이지로 이동")
            }
        })
    }


}

