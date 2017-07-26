package com.pickth.gachi.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.pickth.gachi.view.main.fragments.alarm.AlarmFragment
import com.pickth.gachi.view.main.fragments.chat.ChatFragment
import com.pickth.gachi.view.main.fragments.festival.FestivalFragment
import com.pickth.gachi.view.main.fragments.gachi.GachiFragment
import com.pickth.gachi.view.main.fragments.myinfo.MyinfoFragment
import com.pickth.gachi.view.main.fragments.search.SearchFragment

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class MainPagerAdapter(val fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager), MainPagerModel {

    private val mFestivalFragment = FestivalFragment.getInstance()
    private val mGachiFragment = GachiFragment.getInstance()
    private val mChatFragment = ChatFragment.getInstance()
    private val mAlarmFragment = AlarmFragment.getInstance()
    private val mMyinfoFragment = MyinfoFragment.getInstance()
    private val mSearchFragment = SearchFragment.getInstance()

    private val itemList = ArrayList<Int>()

    override fun getItem(position: Int): Fragment = when(position) {
        1 -> mGachiFragment
        2 -> mChatFragment
        3 -> mAlarmFragment
        4 -> mMyinfoFragment
        else -> mFestivalFragment
    }

    override fun setListItem(position: Int) {
        itemList.add(position)
    }

    override fun getListItem(position: Int): Int = itemList[position]

    override fun getCount(): Int = itemList.size
}