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

package com.pickth.gachi.view.chat

import android.util.Log
import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.net.service.GachiService
import com.pickth.gachi.view.chat.adapter.ChatDetailAdapterContract
import com.pickth.gachi.view.chat.adapter.ChatMessage
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ChatDetailPresenter: ChatDetailContract.Presenter {

    private lateinit var mView: ChatDetailContract.View
    private lateinit var mAdapterView: ChatDetailAdapterContract.View
    private lateinit var mAdapterModel: ChatDetailAdapterContract.Model
    private lateinit var mParticipantAdapter: ParticipantAdapter

    override fun attachView(view: BaseView<*>) {
        mView = view as ChatDetailContract.View
    }

    override fun setChatDetailAdapterView(view: ChatDetailAdapterContract.View) {
        mAdapterView = view
    }

    override fun setChatDetailAdapterModel(model: ChatDetailAdapterContract.Model) {
        mAdapterModel = model
    }

    override fun setParticipantAdapter(adapter: ParticipantAdapter) {
        mParticipantAdapter = adapter
    }

    override fun getItemCount(): Int = mAdapterModel.getItemCount()

    override fun sendMessage(msg: String) {
        val date = SimpleDateFormat("hh : mm a").format(Date(System.currentTimeMillis())).toString()
        mAdapterModel.addItem(ChatMessage(msg, date ,0))

        // test input
        mAdapterModel.addItem(ChatMessage("test", date ,1))
        mView.scrollToPosition(getItemCount())
    }

    override fun getGachiInfo() {
        GachiService().getGachiInfo(mView.getLid())
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                        Log.d("Gachi__ChatPresenter", "getGachiInfo onResponse, code: ${response.code()}")
                        if(response.code() != 200) return

                        mView.bindGachiInfo(response.body()?.string()!!)
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    }

                })
    }
}