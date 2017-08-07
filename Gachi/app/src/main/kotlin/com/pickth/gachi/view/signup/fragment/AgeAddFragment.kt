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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseAddInfoFragment
import kotlinx.android.synthetic.main.fragment_signup_add_text.view.*

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

        rootView.btn_add_info_next.setOnClickListener {
            mListener?.onChange()
        }

        return rootView
    }
}