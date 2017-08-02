package com.pickth.gachi.adapter.pager

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
interface MainPagerModel {
    fun changeBetweenFragment()

    fun setListItem(position: Int)

    fun getListItem(position: Int): Int

    fun getCount(): Int
}