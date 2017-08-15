package com.pickth.gachi.adapter.pager

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.pickth.gachi.view.main.fragments.TapBaseFragment
import com.pickth.gachi.view.main.fragments.alarm.AlarmFragment
import com.pickth.gachi.view.main.fragments.chat.ChatFragment
import com.pickth.gachi.view.main.fragments.festival.FestivalFragment
import com.pickth.gachi.view.main.fragments.myinfo.MyinfoFragment
import com.pickth.gachi.view.main.fragments.search.SearchFragment

/**
 * Extend FragmentStatePagerAdapter, Because FragmentPagerAdapter is not call getItem at calling notifyDataSetChanged
 */
class MainPagerAdapter(val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager), MainPagerModel {

    private val mFestivalFragment = FestivalFragment.getInstance()
//    private val mGachiFragment = GachiFragment.getInstance()
    private val mChatFragment = ChatFragment.getInstance()
    private val mAlarmFragment = AlarmFragment.getInstance()
    private val mMyinfoFragment = MyinfoFragment.getInstance()
    private val mSearchFragment = SearchFragment.getInstance()

    private var mSwitchFragment: TapBaseFragment? = null
    var isSwitch = false

    private val itemList = ArrayList<Int>()

    override fun getItem(position: Int): TapBaseFragment = when(position) {
//        1 -> mGachiFragment
        1 -> mChatFragment
        2 -> mAlarmFragment
        3 -> mMyinfoFragment
        else -> {
            if(mSwitchFragment == null) {
                mSwitchFragment = mFestivalFragment
                mSearchFragment.setMainPagerAdapter(this)
            }

            mSwitchFragment!!
        }
    }

    /**
     * If fragment is festival fragment, change to search fragment
     * If it is upside down, likewise it is the same.
     */
    override fun changeBetweenFragment() {
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
            isSwitch = true
            return PagerAdapter.POSITION_NONE
        }

        if(`object` is SearchFragment && mSwitchFragment is FestivalFragment) {
            isSwitch = false
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