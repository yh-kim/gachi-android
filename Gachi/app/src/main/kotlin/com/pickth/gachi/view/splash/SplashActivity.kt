package com.pickth.gachi.view.splash

import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.util.UserInfoManager
import com.pickth.gachi.view.login.LoginActivity
import com.pickth.gachi.view.main.MainActivity
import com.pickth.gachi.view.signup.AddInfoActivity
import org.jetbrains.anko.startActivity

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val user = FirebaseAuth.getInstance().currentUser
        if(user != null) {
            Log.d(TAG, "onAuthStateChanged:signed_in: ${user.photoUrl}")
            user.getIdToken(true)
                    .addOnCompleteListener {
                        if(it.isSuccessful) {
                            Log.d(TAG, "user token: ${it.result.token.toString()}")
                            UserInfoManager.firebaseUserToken = it.result.token.toString()
                            Log.d(TAG, "user info: ${UserInfoManager.getUser(this).toString()}")
                            val user = UserInfoManager.getUser(this)
                            if(user?.isAddInfo == false) {
                                startActivity<AddInfoActivity>()
                                finish()
                            } else {
                                startActivity<MainActivity>()
                                finish()
                            }
                        }
                    }

        } else {
            Log.d(TAG, "onAuthStateChanged:signed_out")
            startActivity<LoginActivity>()
            finish()
        }

    }

    override fun onBackPressed() {
    }

}
