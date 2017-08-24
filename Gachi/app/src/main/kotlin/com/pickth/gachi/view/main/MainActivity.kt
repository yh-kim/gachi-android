package com.pickth.gachi.view.main

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.pickth.commons.extensions.hideKeyboard
import com.pickth.gachi.R
import com.pickth.gachi.adapter.pager.MainPagerAdapter
import com.pickth.gachi.base.BaseActivity
import com.pickth.gachi.view.custom.MyBottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by yonghoon on 2017-07-09.
 * Mail   : yonghoon.kim@pickth.com
 */
class MainActivity : BaseActivity(), MainContract.View, ViewPager.OnPageChangeListener {

    private lateinit var mMainPresenter: MainPresenter
    private var mMainPagerAdapter: MainPagerAdapter? = null
    private var mViewPager: ViewPager? = null
    private lateinit var mNavigation: MyBottomNavigationView
//    private lateinit var mMenuItem: MenuItem
    private lateinit var prevBottomNavigation: MenuItem

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_festival -> {
                if(mViewPager?.currentItem == 0) {
                    mMainPagerAdapter?.getItem(0)?.clickAgain()
                } else {
                    mViewPager?.currentItem = 0
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_chat -> {
                if(mViewPager?.currentItem == 1) {
                    mMainPagerAdapter?.getItem(1)?.clickAgain()
                } else {
                    mViewPager?.currentItem = 1
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_alarm -> {
                if(mViewPager?.currentItem == 2) {
                    mMainPagerAdapter?.getItem(2)?.clickAgain()
                } else {
                    mViewPager?.currentItem = 2
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_myinfo -> {
                mViewPager!!.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // actionbar
        setSupportActionBar(main_toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = "GACHI"

        // presenter
        mMainPresenter = MainPresenter()
        mMainPresenter.attachView(this)

        // navigation
        mNavigation = navigation
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mMainPagerAdapter = MainPagerAdapter(supportFragmentManager).apply {
            setListItem(0)
            setListItem(1)
            setListItem(2)
            setListItem(3)
            notifyDataSetChanged()
        }

        mViewPager = view_pager.apply {
            adapter = mMainPagerAdapter
            currentItem = 0
            offscreenPageLimit = 4
            addOnPageChangeListener(this@MainActivity)
        }

        prevBottomNavigation = mNavigation.menu.getItem(0)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun changeFestivalAndSearch() {
        mMainPagerAdapter?.changeBetweenFragment()

        isSearch().let {
//            mMenuItem.isVisible = !it
            supportActionBar?.setDisplayShowTitleEnabled(!it)
            supportActionBar?.setDisplayHomeAsUpEnabled(it)
        }

    }

    override fun isSearch(): Boolean = mMainPagerAdapter!!.isSwitch

    override fun onPageSelected(position: Int) {
        prevBottomNavigation.isChecked = false
        prevBottomNavigation = mNavigation.menu.getItem(position)
        prevBottomNavigation.isChecked = true

//        mMenuItem.isVisible = (position == 0 && !isSearch())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)

//        mMenuItem = menu?.findItem(R.id.menu_change_fragment)!!
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_change_fragment -> {
                changeFestivalAndSearch()
            }

            android.R.id.home -> {
                hideKeyboard()
                Handler().postDelayed({changeFestivalAndSearch()}, 100)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(mViewPager?.currentItem == 0 && mMainPagerAdapter!!.isSwitch) {
            changeFestivalAndSearch()
        } else {
            super.onBackPressed()
        }

    }
}
