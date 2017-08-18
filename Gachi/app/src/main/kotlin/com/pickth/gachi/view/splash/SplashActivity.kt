package com.pickth.gachi.view.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
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
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    companion object {
        val TAG = "GACHI_SplashActivity"
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        if(mAuthListener != null) mAuth.removeAuthStateListener(mAuthListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
            var user = it.currentUser
            if(user != null) {
                Log.d(TAG, "onAuthStateChanged:signed_in: ${user.uid} ${user.photoUrl}")

                // 0.8 sec
                Handler().postDelayed(mTimer, 8*100)
            } else {
                Log.d(TAG, "onAuthStateChanged:signed_out")
                startActivity<LoginActivity>()
                finish()
            }
        }

    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

    private val mTimer = Runnable {
        startActivity<MainActivity>()
        finish()
    }
}
