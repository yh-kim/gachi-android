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
import android.view.Menu
import android.view.MenuItem
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.view.custom.AddInfoViewPager
import com.pickth.gachi.view.signup.fragment.*
import kotlinx.android.synthetic.main.activity_add_info.*
import kotlinx.android.synthetic.main.view_pager_add_info.*

class AddInfoActivity: BaseActivity() {

    private lateinit var mViewPager: AddInfoViewPager
    private lateinit var mMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        // actionbar
        setSupportActionBar(addinfo_toolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_back)
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        val test1 = NicknameAddFragment.getInstance()
        val test2 = AgeAddFragment.getInstance()
        val test3 = GenderAddFragment.getInstance()
        val test4 = RegionAddFragment.getInstance()
        val test5 = GenreAddFragment.getInstance()

        mViewPager = vf_add_info.apply {
            setFragmentManager(supportFragmentManager)

            addItemView(test1)
            addItemView(test2)
            addItemView(test3)
            addItemView(test4)
            addItemView(test5)
            notifyDataSetChanged()
        }

        btn_add_info_next_bottom.setOnClickListener {
            mMenuItem.isVisible = true
            mViewPager.getFragments(mViewPager.currentIndex).clickNextButton(false)

            if (mViewPager.currentIndex == 4) {
                btn_add_info_next_bottom.text = resources.getString(R.string.apply)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                if(mViewPager.currentIndex == 0) finish()
                else if(mViewPager.currentIndex == 1) mMenuItem.isVisible = false
                else {
                    mViewPager.changePreFragment()

                    if(mViewPager.currentIndex == 3) {
                        btn_add_info_next_bottom.text = resources.getString(R.string.next)
                    }
                }
            }

            R.id.add_info_skip -> {
                mViewPager.getFragments(mViewPager.currentIndex).clickNextButton(true)

                if (mViewPager.currentIndex == 4) {
                    btn_add_info_next_bottom.text = resources.getString(R.string.apply)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_info_menu, menu)

        mMenuItem = menu.findItem(R.id.add_info_skip)
        mMenuItem.isVisible = false
        return super.onCreateOptionsMenu(menu)
    }
}