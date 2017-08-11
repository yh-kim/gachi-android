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

package com.pickth.gachi.view.main.fragments.search

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.net.service.FestivalService
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival
import com.pickth.gachi.view.main.fragments.search.adapter.SearchAdapterContract
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response

class SearchPresenter: SearchContract.Presenter, OnItemClickListener {

    private lateinit var mView: SearchContract.View
    private lateinit var mAdapterView: SearchAdapterContract.View
    private lateinit var mAdapterModel: SearchAdapterContract.Model

    override fun attachView(view: BaseView<*>) {
        mView = view as SearchContract.View
    }

    override fun setAdapterView(view: SearchAdapterContract.View) {
        mAdapterView = view
        mAdapterView.setItemClickListener(this)
    }

    override fun setAdapterModel(model: SearchAdapterContract.Model) {
        mAdapterModel = model
    }

    override fun searchFestivalList(text: String) {
        FestivalService().searchFestival(text)
                .enqueue(object: retrofit2.Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        if(response?.code() != 200) return

                        val retArr = JSONArray(response.body()!!.string())
                        for(position in 0..retArr.length() - 1) {
                            retArr.getJSONObject(position).let {
                                val title = it.getString("title")
                                val image = it.getString("image")
                                val from = it.getString("from").split("T")[0].replace("-",".")
                                val until = it.getString("until").split("T")[0].replace("-",".")

                                var date = "$from - $until"
                                mAdapterModel.addItem(Festival(date, image, title))
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    }

                })
    }

    override fun clearList() {
        mAdapterModel.clearItems()
    }

    override fun onItemClick(position: Int) {
        mView.intentToFestivalDetailActivity(0)
    }
}