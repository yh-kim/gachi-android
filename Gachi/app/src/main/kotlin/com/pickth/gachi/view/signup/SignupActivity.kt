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
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignupActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        tv_signup_submit.setOnClickListener {
            val email = et_signup_email.text.toString().trim()
            if(!isEmailValid(email)) {
                toast("invalid email")
                return@setOnClickListener
            }

            val password = et_signup_password.text.toString().trim()
            if(!isPasswordValid(password)) {
                toast("invalid password")
                return@setOnClickListener
            }

            startActivity<AddInfoActivity>()
            finish()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }
}