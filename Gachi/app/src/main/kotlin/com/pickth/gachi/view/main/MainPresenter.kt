package com.pickth.gachi.view.main

import android.util.Log
import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.net.service.UserService
import com.pickth.gachi.util.UserInfoManager
import com.pickth.gachi.view.gachi.Gachi
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
class MainPresenter(): MainContract.Presenter {

    lateinit private var mMainView: MainContract.View

    override fun attachView(view: BaseView<*>) {
        this.mMainView =  view as MainContract.View

        // if guest
        if(getUser() == null) return

        val context = mMainView.getContext()
        val uid = UserInfoManager.getUser(context)!!.uid
        UserService().getUser(UserInfoManager.firebaseUserToken, uid)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                        Log.d("Gachi__MainPresenter", "getUser onResponse, code: ${response.code()}")
                        val json = JSONObject(response.body()?.string())
                        Log.d("Gachi__MainPresenter", "getUser onResponse, json: ${json}")

                        val init_step = json.getInt("init_step")
                        val fbid = json.getString("fbid")
                        val profileImage = json.getString("profile_image")
                        val nickname = json.getString("nickname")
                        val age = json.getString("age")
                        val gender = json.getString("gender")
                        val location = json.getString("location")

                        // gachi
                        var gachiArray = ArrayList<Gachi>()
                        val gachis = json.getJSONArray("leadrooms")
                        for(i in 0..gachis.length() - 1) {
                            var gachi = gachis.getJSONObject(i)
                            var lid = gachi.getString("leadroom_id")
                            var title = gachi.getString("detail")
                            var leaderProfile = gachi.getJSONObject("leader")
                                    .getString("profile_image")
                            gachiArray.add(Gachi(lid, title, leaderProfile))
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

                        UserInfoManager.setUser(context, user)
                        Log.d("Gachi__MainPresenter", "user info: ${UserInfoManager.getUser(context).toString()}")
                    }
                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        Log.d("Gachi__MainPresenter", "getUser on Failure")
                    }


                })
    }

    override fun getUser(): UserInfoManager.User? = UserInfoManager.getUser(mMainView.getContext())
}