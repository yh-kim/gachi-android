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
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by yonghoon on 2017-08-25
 */

class GachiService {
    val service = RetrofitManager.getService(GachiAPI::class.java) as GachiAPI

    fun getGachiInfo(lid: String) = service.getGachiInfo(lid)

    interface GachiAPI {
        @GET("leadroom/{lid}")
        fun getGachiInfo(@Path("lid") lid: String): Call<ResponseBody>
    }
}