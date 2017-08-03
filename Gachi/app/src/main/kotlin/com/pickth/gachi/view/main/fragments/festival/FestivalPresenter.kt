package com.pickth.gachi.view.main.fragments.festival

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.main.fragments.festival.adapter.FestivalAdapter

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class FestivalPresenter: FestivalContract.Presenter, OnItemClickListener {

    private lateinit var mView: FestivalContract.View
    private lateinit var mPopularAdapter: FestivalAdapter
    private lateinit var mImmediateAdapter: FestivalAdapter

    override fun attachView(view: BaseView<*>) {
        this.mView = view as FestivalContract.View
    }

    override fun setPopularAdapter(adapter: FestivalAdapter) {
        mPopularAdapter = adapter
        mPopularAdapter.setItemClickListener(this)
    }

    override fun setImmediateAdapter(adapter: FestivalAdapter) {
        mImmediateAdapter = adapter
        mImmediateAdapter.setItemClickListener(this)
    }

    override fun onItemClick(position: Int) {
        mView.intentToFestivalDetailActivity(0)
    }
}