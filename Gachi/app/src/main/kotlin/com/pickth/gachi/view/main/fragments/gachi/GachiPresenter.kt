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

package com.pickth.gachi.view.main.fragments.gachi

import android.content.Context
import android.view.View
import com.pickth.gachi.view.main.fragments.gachi.adapter.Gachi
import com.pickth.gachi.view.main.fragments.gachi.adapter.GachiAdapterContract

class GachiPresenter: GachiContract.Presenter {

    lateinit private var mView: View
    lateinit private var mGachiView: GachiAdapterContract.View
    lateinit private var mGachiModel: GachiAdapterContract.Model

    override fun setGachiAdapterView(gachiView: GachiAdapterContract.View) {
        mGachiView = gachiView
    }

    override fun setGachiAdapterModel(gachiModel: GachiAdapterContract.Model) {
        mGachiModel = gachiModel
    }

    override fun attachView(view: View, context: Context) {
        mView = view
    }

    fun addTest() {
        for(i in 0..10) {
            mGachiModel.addItem(Gachi("${i}번째 가치입니다.", i*10))
        }
    }
}