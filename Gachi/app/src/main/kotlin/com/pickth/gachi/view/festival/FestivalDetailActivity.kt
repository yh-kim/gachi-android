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
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.util.MyBlurTransformation
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.util.StringFormat
import com.pickth.gachi.view.festival.adapter.FestivalDetailAdapter
import com.pickth.gachi.view.gachi.Gachi
import kotlinx.android.synthetic.main.activity_festival_detail.*
import org.json.JSONObject

class FestivalDetailActivity: BaseActivity(), FestivalDetailContract.View {

    private lateinit var mPresenter: FestivalDetailContract.Presenter

    private lateinit var ivFestivalBlurBackground: ImageView
    private lateinit var ivFestivalDetail: ImageView
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

        // presenter
        mPresenter = FestivalDetailPresenter().apply {
            attachView(this@FestivalDetailActivity)
            getFestivalInfo(getFid())
        }

        val adapter = FestivalDetailAdapter()
        rv_festival_gachi.run {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
            addItemDecoration(MyDividerItemDecoration(context, LinearLayoutManager.HORIZONTAL, 30, false))
        }

        // test input
        for(i in 0..4) adapter.addItem(Gachi("b", 0))

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getFid(): String = intent.getStringExtra("fid")

    override fun bindFestivalInfo(info: String) {
        JSONObject(info).let {
            val title = it.getString("title")
            val starring = it.getString("starring")
            val from = it.getString("from")
            val until = it.getString("until")
            val image = it.getString("image")
            val detail = it.getString("detail")
            val location = it.getString("location")

            tv_festival_detail_title.text = title
            tv_festival_detail_lineup.text = starring
            tv_festival_detail_date.text = StringFormat.formatFestivalDate(from, until)
            tv_festival_detail_detail.text = detail

            tv_festival_detail_detail.post {
                Log.d(TAG, "festival detail line count: ${tv_festival_detail_detail.lineCount}")
                if(tv_festival_detail_detail.lineCount > 2) {
                    Log.d(TAG, "is ellipsis?  ${tv_festival_detail_detail.layout.getEllipsisCount(2) > 0}  ${tv_festival_detail_detail.layout.getEllipsisCount(2)}")

                    tv_festival_detail_info_more.visibility = View.VISIBLE
                    tv_festival_detail_info_more.setOnClickListener {
                        tv_festival_detail_info_more.ellipsize = null
                        tv_festival_detail_detail.maxLines = Integer.MAX_VALUE
                        tv_festival_detail_info_more.visibility = View.GONE
                    }
                }
            }

            Glide.with(this)
                    .load(image)
                    .into(iv_festival_detail)

            Glide.with(this)
                    .load(image)
                    .apply(RequestOptions.bitmapTransform(MyBlurTransformation(this)))
                    .into(object: SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable?, transition: Transition<in Drawable>?) {
                            iv_festival_blur_background.background = resource
                        }
                    })
        }
    }
}