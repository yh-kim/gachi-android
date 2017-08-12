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

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.util.MyBlurTransformation
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.main.fragments.gachi.adapter.Gachi
import kotlinx.android.synthetic.main.activity_festival_detail.*

class FestivalDetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festival_detail)


//        val icon = ContextCompat.getDrawable(this, R.drawable.ic_gachi)
        val icon = AppCompatResources.getDrawable(this, R.drawable.ic_back)!!
        DrawableCompat.setTint(icon, ContextCompat.getColor(this, R.color.colorWhite))

        setSupportActionBar(festival_toolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(icon)
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        val adapter = FestivalDetailAdapter()
        rv_festival_gachi.run {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
            addItemDecoration(MyDividerItemDecoration(context, LinearLayoutManager.HORIZONTAL, 15, false))
        }



        // test input
        for(i in 0..4) adapter.addItem(Gachi("", 0))

        Glide.with(this)
                .load(R.drawable.test)
                .apply(RequestOptions.bitmapTransform(MyBlurTransformation(this)))
                .into(object: SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable?, transition: Transition<in Drawable>?) {
                        iv_festival_blur_background.background = resource
                    }
                })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }


        return super.onOptionsItemSelected(item)
    }
}