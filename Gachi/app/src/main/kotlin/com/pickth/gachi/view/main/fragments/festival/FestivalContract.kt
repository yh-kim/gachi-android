package com.pickth.gachi.view.main.fragments.festival

import com.pickth.commons.mvp.BasePresenter
import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.view.main.fragments.festival.adapter.FestivalAdapter

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
interface FestivalContract {
    interface View: BaseView<Presenter> {
        fun intentToFestivalDetailActivity(position: Int)
    }

    interface Presenter: BasePresenter {
        fun setPopularAdapter(adapter: FestivalAdapter)

        fun setImmediateAdapter(adapter: FestivalAdapter)
    }
}