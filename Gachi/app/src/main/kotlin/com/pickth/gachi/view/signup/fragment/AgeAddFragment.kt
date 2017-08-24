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
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseAddInfoFragment
import com.pickth.gachi.net.service.UserService
import com.pickth.gachi.util.UserInfoManager
import kotlinx.android.synthetic.main.fragment_signup_add_text.view.*
import okhttp3.ResponseBody
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgeAddFragment : BaseAddInfoFragment() {

    companion object {
        val PAGE_INDEX = 1

        private val mInstance = AgeAddFragment()
        fun getInstance(): AgeAddFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_signup_add_text, container, false)


        rootView.tv_add_info_title.text = resources.getStringArray(R.array.add_info_title)[PAGE_INDEX]

        rootView.et_add_info_input.run {
            hint = "22"
            inputType = (InputType.TYPE_CLASS_NUMBER)
        }

        rootView.tv_add_info_explanation.text = "연령대로 표시됩니다"

        return rootView
    }

    override fun clickNextButton(isSkip: Boolean) {
        Log.d(TAG, "click next button, isSkip: $isSkip")

        if(isSkip) {
            mListener?.onChange()
            return
        }
        val textAge = view!!.et_add_info_input.text.toString()
        if(textAge.length == 0) {
            activity.toast("올바른 값을 입력해주세요")
            return
        }

        val input = textAge.toInt()

        if(input*1 == 0) {
            activity.toast("올바른 값을 입력해주세요")
            return
        }

        initialUserInfo(input)
    }

    fun initialUserInfo(input: Int) {
        Log.d(TAG, "initialUserInfo, age input: $input")
        var map = HashMap<String, String>()
        map.set("age", input.toString())
        UserService()
                .initialUserInfo(UserInfoManager.firebaseUserToken, UserInfoManager.getUser(context)?.uid!!, NicknameAddFragment.PAGE_INDEX + 1, map)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                        Log.d(TAG, "initialUserInfo onResponse, code: ${response.code()}")

                        if (response.code() == 200) {
                            UserInfoManager.getUser(context)?.age = input
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