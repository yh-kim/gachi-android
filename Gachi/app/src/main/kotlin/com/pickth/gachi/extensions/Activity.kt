package com.pickth.gachi.extensions

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
fun Activity.addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
    checkNotNull(fragmentManager)
    checkNotNull(fragment)
    val transaction = fragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}