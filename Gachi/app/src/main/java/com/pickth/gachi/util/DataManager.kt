package com.pickth.gachi.util

import android.content.Context

/**
 * Created by yonghoon on 2017-07-12.
 * Blog   : http://blog.pickth.com
 * Github : https://github.com/yh-kim
 * Mail   : yonghoon.kim@pickth.com
 */
class DataManager {
    companion object {
        fun setAppPreferences(context: Context, key: String, value: String) {
            val pref = context.getSharedPreferences("gachi", 0)
            val prefEditor = pref.edit()
            prefEditor.putString(key, value)
                    .commit()
        }

        fun getAppPreferences(context: Context, key: String): String {
            val prefEditor = context.getSharedPreferences("gachi", 0)
            return prefEditor.getString(key, "")
        }
    }
}