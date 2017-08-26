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

package com.pickth.gachi.view.gachi

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.net.service.GachiService
import kotlinx.android.synthetic.main.activity_gachi_detail.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GachiDetailActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gachi_detail)

        // actionbar
        setSupportActionBar(gachi_toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getGachiInfo()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun getLid(): String = intent.getStringExtra("lid")

    fun getGachiInfo() {
        GachiService()
                .getGachiInfo(getLid())
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                        Log.d(TAG, "getGachiInfo onResponse, code: ${response.code()}")
                        if(response.code() != 200) return

                        val json = JSONObject(response.body()?.string())
                        Log.d(TAG, "getGachiInfo onResponse, json: ${json}")

                        // get info
                        var title = json.getString("detail")
                        var maxNum = json.getInt("max_follower")

                        // get leader
                        val leader = json.getJSONObject("leader")
                        var uid = leader.getString("uid")
                        var nickname = leader.getString("nickname")
                        var profile = leader.getString("profile_image")

                        // bind
                        if(title == "null") title = "no title"
                        tv_gachi_title.text = title
                        tv_gachi_maxNum.text = "${maxNum}명 모집 중"
                        if(profile == "") {
                            Glide.with(applicationContext)
                                    .load(R.drawable.test)
                                    .apply(RequestOptions().circleCrop())
                                    .into(iv_gachi_leader_profile)
                        } else {
                            Glide.with(applicationContext)
                                    .load(profile)
                                    .apply(RequestOptions().circleCrop())
                                    .into(iv_gachi_leader_profile)
                        }
                        tv_gachi_leader_nickname.text = nickname
                        tv_gachi_leader_info.text = "지역 없음\n20대 중반"
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    }

                })
    }
}