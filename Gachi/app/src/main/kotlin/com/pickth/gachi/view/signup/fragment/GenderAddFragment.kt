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

package com.pickth.gachi.view.signup.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseAddInfoFragment
import com.pickth.gachi.net.service.UserService
import com.pickth.gachi.util.UserInfoManager
import kotlinx.android.synthetic.main.fragment_signup_add_gender.view.*
import okhttp3.ResponseBody
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenderAddFragment : BaseAddInfoFragment() {
    private var isMale: Boolean? = null

    companion object {
        val PAGE_INDEX = 2

        private val mInstance = GenderAddFragment()
        fun getInstance(): GenderAddFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_signup_add_gender, container, false)

        rootView.tv_add_info_title.text = resources.getStringArray(R.array.add_info_title)[PAGE_INDEX]

        var gray = ContextCompat.getColor(context, R.color.colorBrightGray00)
        var blue = ContextCompat.getColor(context, R.color.colorPrimary)

        rootView.tv_gender_male.setOnClickListener {
            isMale = true
            rootView.tv_gender_male.isSelected = true
            rootView.tv_gender_female.isSelected = false

            rootView.tv_gender_male.textColor = blue
            rootView.tv_gender_female.textColor = gray
        }

        rootView.tv_gender_female.setOnClickListener {
            isMale = false
            rootView.tv_gender_male.isSelected = false
            rootView.tv_gender_female.isSelected = true

            rootView.tv_gender_male.textColor = gray
            rootView.tv_gender_female.textColor = blue
        }

        return rootView
    }

    override fun clickNextButton(isSkip: Boolean) {
        Log.d(TAG, "click next button, isSkip: $isSkip")

        if(isSkip) {
            mListener?.onChange()
            return
        }

        if(isMale == null) {
            activity.toast("선택하세요")
            return
        }

        initialUserInfo(if(isMale == true) "male" else "female")
    }

    fun initialUserInfo(input: String) {
        Log.d(TAG, "initialUserInfo, gender input: $input")
        var map = HashMap<String, String>()
        map.set("gender", input)
        UserService()
                .initialUserInfo(UserInfoManager.firebaseUserToken, UserInfoManager.getUser(context)?.uid!!, NicknameAddFragment.PAGE_INDEX + 1, map)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                        Log.d(TAG, "initialUserInfo onResponse, code: ${response.code()}")

                        if (response.code() == 200) {
                            UserInfoManager.getUser(context)?.gender = input
                            UserInfoManager.notifyDataSetChanged(context)
                            Log.d(TAG, "user info: ${UserInfoManager.getUser(context).toString()}")
                            mListener?.onChange()
                        }

                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        Log.d(TAG, "initialUserInfo on Failure ${t?.printStackTrace()}")
                    }

                })
    }
}