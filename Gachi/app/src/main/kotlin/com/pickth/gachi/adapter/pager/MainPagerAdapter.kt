package com.pickth.gachi.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.pickth.gachi.view.main.fragments.alarm.AlarmFragment
import com.pickth.gachi.view.main.fragments.chat.ChatFragment
import com.pickth.gachi.view.main.fragments.festival.FestivalFragment
import com.pickth.gachi.view.main.fragments.gachi.GachiFragment
import com.pickth.gachi.view.main.fragments.myinfo.MyinfoFragment
import com.pickth.gachi.view.main.fragments.search.SearchFragment

/**
 * Extend FragmentStatePagerAdapter, Because FragmentPagerAdapter is not call getItem at calling notifyDataSetChanged
 */
class MainPagerAdapter(val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager), MainPagerModel {

    private val mFestivalFragment = FestivalFragment.getInstance()
    private val mGachiFragment = GachiFragment.getInstance()
    private val mChatFragment = ChatFragment.getInstance()
    private val mAlarmFragment = AlarmFragment.getInstance()
    private val mMyinfoFragment = MyinfoFragment.getInstance()
    private val mSearchFragment = SearchFragment.getInstance()

    private var mSwitchFragment: Fragment? = null

    private val itemList = ArrayList<Int>()

    override fun getItem(position: Int): Fragment = when(position) {
        1 -> mGachiFragment
        2 -> mChatFragment
        3 -> mAlarmFragment
        4 -> mMyinfoFragment
        else -> {
            if(mSwitchFragment == null) {
                mSwitchFragment = mFestivalFragment
            }

            mSwitchFragment!!
        }
    }

    /**
     * If fragment is festival fragment, change to search fragment
     * If it is upside down, likewise it is the same.
     */
    fun changeBetweenFragment() {
        fragmentManager.beginTransaction().remove(mSwitchFragment).commit()

        if(mSwitchFragment is FestivalFragment) {
            mSwitchFragment = mSearchFragment
        } else {
            mSwitchFragment = mFestivalFragment
        }

        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any?): Int {
        if(`object` is FestivalFragment && mSwitchFragment is SearchFragment) {
            return PagerAdapter.POSITION_NONE
        }

        if(`object` is SearchFragment && mSwitchFragment is FestivalFragment) {
            return PagerAdapter.POSITION_NONE
        }

        return PagerAdapter.POSITION_UNCHANGED
    }

    override fun setListItem(position: Int) {
        itemList.add(position)
    }

    override fun getListItem(position: Int): Int = itemList[position]

    override fun getCount(): Int = itemList.size
}