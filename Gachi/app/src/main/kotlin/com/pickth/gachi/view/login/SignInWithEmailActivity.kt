/*
 * Copyright 2017 Yonghoon Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pickth.gachi.view.login

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.pickth.commons.extensions.hideKeyboard
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.net.service.UserService
import com.pickth.gachi.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_signup.*
import okhttp3.ResponseBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by yonghoon on 2017-08-21
 */

class SignInWithEmailActivity: BaseActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // actionbar
        setSupportActionBar(signup_toolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        // firebase
        mAuth = FirebaseAuth.getInstance()

        tv_signup_title.text = "sign in with email"
        tv_signup_submit.text = "sign in"

        tv_signup_submit.setOnClickListener {
            hideKeyboard()

            val email = et_signup_email.text.toString().trim()
            if(email.length == 0 ) {
                toast("이메일을 입력해주세요")
                return@setOnClickListener
            }

            val password = et_signup_password.text.toString().trim()
            if(password.length == 0 ) {
                toast("비밀번호를 입력해주세요")
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        Log.d(TAG, "signInWithEmailAndPassword:onComplete:" + it.isSuccessful);

                        if (!it.isSuccessful) {
                            Log.d(TAG, "signInWithEmailAndPassword, : ${it.exception}")
                            toast("유효하지 않은 정보입니다.")
                        } else {
                            it.result.user.getIdToken(true)
                                    .addOnCompleteListener {
                                        val token = it.result.token
                                        Log.d(TAG, "user token: ${token}")

                                        UserService().getUserId(token!!)
                                                .enqueue(object: Callback<ResponseBody> {
                                                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                                                        Log.d(TAG, "getUserId onResponse, code: ${response.code()}")
                                                        val uid = JSONObject(response.body()?.string()).getString("uid")
                                                        Log.d(TAG, "getUserId onResponse, uid: ${uid}")

                                                        startActivity<MainActivity>()
                                                        finish()
                                                    }

                                                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                                                        Log.d(TAG, "getUserId on Failure")
                                                    }

                                                })
                                    }


                        }
                    }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                startActivity<LoginActivity>()
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        startActivity<LoginActivity>()
        finish()
    }
}