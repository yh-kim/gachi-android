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

package com.pickth.gachi.net.service

import com.pickth.gachi.net.RetrofitManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by yonghoon on 2017-08-21
 */

class UserService {
    val service = RetrofitManager.getService(UserAPI::class.java) as UserAPI

    fun getUserId(token: String) = service.getUserID(token)

    fun getUser(token: String, uid: String) = service.getUser(token, uid)

    fun initialUserInfo(token: String, uid: String, step: Int, input: Map<String, String>) = service.initialUserInfo(token, uid, step, input)

    interface UserAPI {
        @GET("user/login")
        fun getUserID(@Header("authorization") token: String): Call<ResponseBody>

        @GET("user/{uid}")
        fun getUser(@Header("authorization") token: String, @Path("uid") uid: String): Call<ResponseBody>

        @FormUrlEncoded
        @PATCH("user/{uid}/initial")
        fun initialUserInfo(@Header("authorization") token: String, @Path("uid") uid: String, @Field("step") step: Int, @FieldMap input: Map<String, String>): Call<ResponseBody>
//        fun initialUserInfo(@Header("authorization") token: String, @Path("uid") uid: String, @Field("step") step: Int, @Field("nickname") nickname: String): Call<ResponseBody>

    }
}