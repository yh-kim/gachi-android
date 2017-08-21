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

package com.pickth.gachi.view.signup

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
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

class SignUpActivity : BaseActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener

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

        tv_signup_submit.setOnClickListener {
            val email = et_signup_email.text.toString().trim()
            if(!isEmailValid(email)) {
                toast("invalid email")
                return@setOnClickListener
            }

            val password = et_signup_password.text.toString().trim()
            if(!isPasswordValid(password)) {
                toast("invalid password")
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + it.isSuccessful);

                        if (!it.isSuccessful) {
                            Log.d(TAG, "createUserWithEmailAndPassword: ${it.exception}")
                            Log.d(TAG, "add user not successful " + it.isSuccessful);
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
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        if(mAuthListener != null) mAuth.removeAuthStateListener(mAuthListener)
    }
}