package com.pickth.gachi.view.main.fragments.festival

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
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

        setHasOptionsMenu(true)

        mPresenter = FestivalPresenter()
        mPresenter.attachView(this)

        mPopularAdapter = FestivalAdapter()
        mImmediateAdapter = FestivalAdapter()

        rootView.rv_main_festival_popular.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mPopularAdapter
        }

        rootView.rv_main_festival_immediate.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mImmediateAdapter
        }

        rootView.tv_view_all_popular.setOnClickListener {
            activity.startActivity<FestivalDetailActivity>()
        }

        // test input
        for(i in 0..4) {
            mPopularAdapter.addItem(Festival("", "", ""))
            mImmediateAdapter.addItem(Festival("", "", ""))
        }

        return rootView
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.findItem(R.id.menu_change_fragment)?.isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun clickAgain() {
    }

}