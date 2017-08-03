package com.pickth.gachi.view.main.fragments.festival

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.base.BaseFragment
import com.pickth.gachi.view.festival.FestivalDetailActivity
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival
import com.pickth.gachi.view.main.fragments.festival.adapter.FestivalAdapter
import kotlinx.android.synthetic.main.fragment_main_festival.view.*
import org.jetbrains.anko.startActivity

class FestivalFragment: BaseFragment(), FestivalContract.View {

    private lateinit var mPresenter: FestivalPresenter
    private lateinit var mPopularAdapter: FestivalAdapter
    private lateinit var mImmediateAdapter: FestivalAdapter

    companion object {
        private val mInstance = FestivalFragment()
        fun getInstance(): FestivalFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main_festival, container, false)

        mPopularAdapter = FestivalAdapter()
        mImmediateAdapter = FestivalAdapter()

        mPresenter = FestivalPresenter().apply {
            attachView(this@FestivalFragment)
            setPopularAdapter(mPopularAdapter)
            setImmediateAdapter(mImmediateAdapter)
        }

        rootView.rv_main_festival_popular.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mPopularAdapter
        }

        rootView.rv_main_festival_immediate.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mImmediateAdapter
        }

        // test input
        for(i in 0..4) {
            mPopularAdapter.addItem(Festival("", "", ""))
            mImmediateAdapter.addItem(Festival("", "", ""))
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
    }

    override fun intentToFestivalDetailActivity(position: Int) {
        activity.startActivity<FestivalDetailActivity>()
    }

    override fun clickAgain() {
    }

}