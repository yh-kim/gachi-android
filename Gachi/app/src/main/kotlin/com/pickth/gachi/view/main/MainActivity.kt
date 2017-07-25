package com.pickth.gachi.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.pickth.commons.activities.BaseActivity
import com.pickth.gachi.R
import com.pickth.gachi.adapter.pager.MainPagerAdapter
import com.pickth.gachi.view.custom.MyBottomNavigationView

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
class MainActivity : BaseActivity(), MainContract.View, ViewPager.OnPageChangeListener {

    private lateinit var mMainPresenter: MainPresenter
    private var mMainPagerAdapter: MainPagerAdapter? = null
    private var mViewPager: ViewPager? = null
    private lateinit var mNavigation: MyBottomNavigationView

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_festival -> {
                mViewPager!!.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_gachi -> {
                mViewPager!!.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_chat -> {
                mViewPager!!.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_alarm -> {
                mViewPager!!.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_myinfo -> {
                mViewPager!!.currentItem = 4
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainPresenter = MainPresenter()
        mMainPresenter.attachView(this)

        mViewPager = findViewById(R.id.view_pager) as ViewPager
        mNavigation = findViewById(R.id.navigation) as MyBottomNavigationView
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mMainPagerAdapter = MainPagerAdapter(supportFragmentManager)

        mViewPager?.run {
            adapter = mMainPagerAdapter
            currentItem = 0
            offscreenPageLimit = 5
        }

        mViewPager!!.addOnPageChangeListener(this)
        prevBottomNavigation = mNavigation.menu.getItem(0)

        mMainPagerAdapter?.run {
            setListItem(0)
            setListItem(1)
            setListItem(2)
            setListItem(3)
            setListItem(4)
            notifyDataSetChanged()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    private lateinit var prevBottomNavigation: MenuItem
    override fun onPageSelected(position: Int) {
        prevBottomNavigation.isChecked = false
        prevBottomNavigation = mNavigation.menu.getItem(position)
        prevBottomNavigation.isChecked = true
    }
}
