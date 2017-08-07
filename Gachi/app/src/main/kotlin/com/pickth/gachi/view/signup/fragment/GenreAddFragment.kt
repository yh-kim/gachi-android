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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.commons.extensions.intent
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseAddInfoFragment
import com.pickth.gachi.view.main.MainActivity
import kotlinx.android.synthetic.main.fragment_signup_add_genre.view.*

class GenreAddFragment : BaseAddInfoFragment() {

    companion object {
        val PAGE_INDEX = 4

        private val mInstance = GenreAddFragment()
        fun getInstance(): GenreAddFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_signup_add_genre, container, false)

        rootView.tv_add_info_title.text = resources.getStringArray(R.array.add_info_title)[PAGE_INDEX]

        rootView.btn_add_info_next.text = resources.getString(R.string.apply)

        rootView.btn_add_info_next.setOnClickListener {
            intent(MainActivity::class.java)
            activity.finish()
        }

        return rootView
    }
}