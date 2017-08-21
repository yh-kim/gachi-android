package com.pickth.gachi.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
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

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener

    private lateinit var mCallbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
            var user = it.currentUser
            if(user != null) {
                Log.d(TAG, "onAuthStateChanged:signed_in")
                startActivity<MainActivity>()
                finish()
            } else {
                Log.d(TAG, "onAuthStateChanged:signed_out")
            }
        }


        mCallbackManager = CallbackManager.Factory.create()

        lb_facebook_login.setReadPermissions("email", "public_profile")
        lb_facebook_login.registerCallback(mCallbackManager, object: FacebookCallback<LoginResult> {
            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(error: FacebookException?) {
                error?.printStackTrace()
                Log.d(TAG, "facebook:onError", error)
            }

            override fun onSuccess(result: LoginResult) {
                Log.d(TAG, "facebook:onSuccess: $result")
                handleFacebookAccessToken(result.accessToken)
            }

        })


        fl_login_with_email.setOnClickListener {
        }

        fl_login_with_facebook.setOnClickListener {
            lb_facebook_login.performClick()
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

    fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken: $token")

        var credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, OnCompleteListener {
                    Log.d(TAG, "signInWithCredential:onComplete: ${it.isSuccessful}")

                    if(!it.isSuccessful) {
                        Log.d(TAG, "signInWithCredential: ${it.exception}")
                        toast("Authentication failed.")
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy();
    }

    private fun getUser() {
        startActivity<MainActivity>()
        finish()
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        if(mAuthListener != null) mAuth.removeAuthStateListener(mAuthListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
    }
}

