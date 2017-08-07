package com.pickth.gachi.net

import com.pickth.gachi.util.StaticUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by yonghoon on 2017-07-18.
 * Mail   : yonghoon.kim@pickth.com
 */

object RetrofitManager {
    private fun getRetrofit(url: String) = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(clazz: Class<*>) = getRetrofit(StaticUrl.BASE_URL).create(clazz)
}