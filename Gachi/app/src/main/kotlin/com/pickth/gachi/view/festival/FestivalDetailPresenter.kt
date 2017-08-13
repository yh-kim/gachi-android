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

package com.pickth.gachi.view.festival

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.net.service.FestivalService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by yonghoon on 2017-08-13
 */

class FestivalDetailPresenter: FestivalDetailContract.Presenter {

    private lateinit var mView: FestivalDetailContract.View

    override fun attachView(view: BaseView<*>) {
        mView = view as FestivalDetailContract.View
    }

    override fun getFestivalInfo(fid: String) {
        FestivalService().getFestivalInfo(fid)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        if (response?.code() != 200) return

                        mView.bindFestivalInfo(response.body()!!.string())
                    }
                })
    }
}