package com.pickth.gachi.view.custom

import android.content.Context
import android.support.design.internal.BottomNavigationItemView
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

        val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false

        if(menuView != null) {
            for(i in 0..menuView.childCount-1) {
                val menuItemView = menuView.getChildAt(i) as BottomNavigationItemView

//                val smallText = menuItemView.findViewById(R.id.smallLabel) as TextView
//                smallText.visibility = View.INVISIBLE


//                val icon = menuItemView.getChildAt(0) as AppCompatImageView
//                val params = icon.layoutParams as LayoutParams
//                params.gravity = Gravity.CENTER

                menuItemView.setShiftingMode(true)
                menuItemView.setChecked(menuItemView.itemData.isChecked)
            }
        }
    }
}