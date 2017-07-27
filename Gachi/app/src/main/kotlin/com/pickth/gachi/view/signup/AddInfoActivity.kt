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

package com.pickth.gachi.view.signup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pickth.gachi.R
import com.pickth.gachi.view.signup.fragment.*
import kotlinx.android.synthetic.main.activity_add_info.*

class AddInfoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        val test1 = Test1Fragment.getInstance()
        val test2 = Test2Fragment.getInstance()
        val test3 = Test3Fragment.getInstance()
        val test4 = Test4Fragment.getInstance()
        val test5 = Test5Fragment.getInstance()

        vf_add_info.setFragmentManager(supportFragmentManager)
        vf_add_info.run {
            setFragmentManager(supportFragmentManager)

            addItemView(test1)
            addItemView(test2)
            addItemView(test3)
            addItemView(test4)
            addItemView(test5)
            notifyDataSetChanged()
        }

    }
}