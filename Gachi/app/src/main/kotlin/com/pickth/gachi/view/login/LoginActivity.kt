package com.pickth.gachi.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.net.service.UserService
import com.pickth.gachi.util.UserInfoManager
import com.pickth.gachi.view.main.MainActivity
import com.pickth.gachi.view.signup.AddInfoActivity
import com.pickth.gachi.view.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

                Log.d(TAG, "user info: ${UserInfoManager.getUser(this).toString()}")

                user.getIdToken(true)
                        .addOnCompleteListener {
                            val userToken = it.result.token
                            Log.d(TAG, "user token: ${userToken}")
                            UserInfoManager.firebaseUserToken = it.result.token.toString()
                            getUid(userToken)
                        }

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
            startActivity<SignInWithEmailActivity>()
            finish()
        }

        fl_login_with_facebook.setOnClickListener {
            lb_facebook_login.performClick()
        }

        tv_signup.setOnClickListener {
            startActivity<SignUpActivity>()
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
                .addOnCompleteListener {
                    Log.d(TAG, "signInWithCredential:onComplete: ${it.isSuccessful}")


                    if(!it.isSuccessful) {
                        Log.d(TAG, "signInWithCredential: ${it.exception}")
                        toast("Authentication failed.")
                        return@addOnCompleteListener
                    }
                }
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

    fun getUid(userToken: String?) {
        UserService().getUserId(userToken!!)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                        Log.d(TAG, "getUserId onResponse, code: ${response.code()}")
                        val uid = JSONObject(response.body()?.string()).getString("uid")
                        Log.d(TAG, "getUserId onResponse, uid: ${uid}")

                        UserService().getUser(userToken, uid)
                                .enqueue(object: Callback<ResponseBody> {
                                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                                        Log.d(TAG, "getUser onResponse, code: ${response.code()}")
                                        val json = JSONObject(response.body()?.string())
                                        Log.d(TAG, "getUser onResponse, json: ${json}")

                                        val init_step = json.getInt("init_step")
                                        val fbid = json.getString("fbid")
                                        val profileImage = json.getString("profile_image")
                                        val nickname = json.getString("nickname")
                                        val age = json.getString("age")
                                        val gender = json.getString("gender")
                                        val location = json.getString("location")

                                        // gachi
                                        var gachiArray = ArrayList<String>()
                                        val gachis = json.getJSONArray("leadrooms")
                                        for(i in 0..gachis.length() - 1) {
                                            gachiArray.add(gachis.getJSONObject(i)
                                                    .getString("leadroom_id"))
                                        }

                                        // set user
                                        val user = UserInfoManager.User(uid)

                                        user.isAddInfo = init_step != 0
                                        if(fbid != "null") user.fbid = fbid
                                        if(profileImage != "") user.profileImage = profileImage
                                        if(nickname != "null") user.nickname = nickname
                                        if(age != "null") user.age = age.toInt()
                                        if(gender != "null") user.gender = gender
                                        if(location != "null") user.region = location
                                        if(gachiArray.size != 0) user.gachi = gachiArray

                                        UserInfoManager.setUser(applicationContext, user)
                                        Log.d(TAG, "user info: ${UserInfoManager.getUser(applicationContext).toString()}")

                                        if(user.isAddInfo == true) {
                                            startActivity<MainActivity>()
                                            finish()
                                        } else {
                                            startActivity<AddInfoActivity>()
                                            finish()
                                        }

                                    }
                                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                                        Log.d(TAG, "getUser on Failure")
                                    }


                                })

                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        Log.d(TAG, "getUserId on Failure")
                    }

                })
    }
}

