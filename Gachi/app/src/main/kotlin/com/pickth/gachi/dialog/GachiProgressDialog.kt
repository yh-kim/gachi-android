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

package com.pickth.gachi.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.pickth.gachi.R
import kotlinx.android.synthetic.main.dialog_progress.*
import org.jetbrains.anko.dip

class GachiProgressDialog(context: Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.run {
            setDimAmount(0f)
            setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }

        setContentView(R.layout.dialog_progress)

        val anim = RotateAnimation(0f, 360f, context.dip(48).div(2).toFloat(), context.dip(48).div(2).toFloat())
                .apply {
                    interpolator = LinearInterpolator()
                    repeatCount = Animation.INFINITE
                    duration = 1500
                }

        dialog_image.run{
//            alpha = 0.5f
            startAnimation(anim)
        }

    }

    fun showDialog(): Dialog {
        show()
        return this
    }

    override fun cancel() {
        return
    }
}