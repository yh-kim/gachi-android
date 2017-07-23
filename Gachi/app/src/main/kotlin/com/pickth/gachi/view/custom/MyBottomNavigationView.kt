package com.pickth.gachi.view.custom

import android.content.Context
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class MyBottomNavigationView(context: Context, attrs: AttributeSet): BottomNavigationView(context, attrs) {
    init {
        centerMenuIcon()
    }

    private fun centerMenuIcon() {

        val menuView: BottomNavigationMenuView = getChildAt(0) as BottomNavigationMenuView

        menuView::class.java.getDeclaredField("mShiftingMode").apply { 
            isAccessible = true
            setBoolean(menuView, false)
            isAccessible = false
        }

//        if(menuView != null) {
//            for(i in 0..menuView.childCount-1) {
//                val menuItemView = menuView.getChildAt(i) as BottomNavigationItemView
//
//            }
//        }
    }
}