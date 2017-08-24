package com.pickth.gachi.view.main.fragments.festival

import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.view.festival.adapter.FestivalDetailAdapter
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival
import com.pickth.gachi.view.main.fragments.festival.adapter.FestivalAdapter
import com.pickth.gachi.view.main.fragments.gachi.adapter.Gachi

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
interface FestivalContract {
    interface View: BaseView<Presenter> {
        fun intentToFestivalDetailActivity(fid: String)
    }

    interface Presenter: BasePresenter {
        fun setPopularAdapter(adapter: FestivalAdapter)
        fun setPopularGachiAdapter(adapter: FestivalDetailAdapter)
        fun getPopularFestivalItem(position: Int): Festival
        fun getPopularGachiItem(position: Int): Gachi

        fun getPopularFestivalList()
        fun getPopularGachiList()
    }
}