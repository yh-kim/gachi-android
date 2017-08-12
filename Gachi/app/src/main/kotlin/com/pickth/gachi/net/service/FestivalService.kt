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
import retrofit2.http.Query

/**
 * Created by yonghoon on 2017-08-07
 */

class FestivalService {
    val service = RetrofitManager.getService(FestivalAPI::class.java) as FestivalAPI

    fun getFestivalList(page: Int, type: String = "default") = service.getFestivalList(page = page, type = type)
    /**
     * @param term keyword for search
     * @param page page number of getting items. first page is 1
     */
    fun searchFestival(term: String, page: Int) = service.searchFestival(term, page)

    interface FestivalAPI {
        @GET("festival/list")
        fun getFestivalList(@Query("page") page: Int, @Query("type") type: String): Call<ResponseBody>

        @GET("festival/search")
        fun searchFestival(@Query("term") term: String, @Query("page") page: Int): Call<ResponseBody>
    }
}