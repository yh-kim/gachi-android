package com.pickth.gachi.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by yonghoon on 2017-08-24
 */

object UserInfoManager {
    var firebaseUserToken = ""
    private var mUser: User? = null

    fun getUser(context: Context): User? {
        if(mUser == null) {
            val json = context
                    .getSharedPreferences("gachi", 0)
                    .getString("user", "")

            if(json == "") return null

            val type = object: TypeToken<User>(){}.type
            mUser = Gson().fromJson<User>(json, type)
        }

        return mUser
    }

    fun setUser(context: Context, user: User) {
        mUser = user
        notifyDataSetChanged(context)
    }

    fun clearUserInfo(context: Context) {
        mUser = null
        context.getSharedPreferences("gachi", 0)
                .edit()
                .putString("user", "")
                .apply()
    }

    fun notifyDataSetChanged(context: Context) {
        context.getSharedPreferences("gachi", 0)
                .edit()
                .putString("user", Gson().toJson(mUser).toString())
                .apply()
    }

    data class User(var uid: String,
                    var isAddInfo: Boolean = false,
                    var fbid: String? = null,
                    var profileImage: String? = null,
                    var nickname: String? = null,
                    var age: Int? = null,
                    var gender: String? = null,
                    var region: String? = null,
                    var genre: ArrayList<String>? = null,
                    var gachi: ArrayList<String>? = null)
}