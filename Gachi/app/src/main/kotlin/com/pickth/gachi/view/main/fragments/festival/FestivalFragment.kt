package com.pickth.gachi.view.main.fragments.festival

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pickth.gachi.R
import com.pickth.gachi.util.MyDividerItemDecoration
import com.pickth.gachi.view.festival.FestivalDetailActivity
import com.pickth.gachi.view.main.fragments.TapBaseFragment
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival
import com.pickth.gachi.view.main.fragments.festival.adapter.FestivalAdapter
import kotlinx.android.synthetic.main.fragment_main_festival.view.*
import org.jetbrains.anko.startActivity

class FestivalFragment : TapBaseFragment(), FestivalContract.View {

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
            addItemDecoration(MyDividerItemDecoration(context, LinearLayoutManager.HORIZONTAL, 16, false))
        }

        rootView.rv_main_festival_immediate.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mImmediateAdapter
            addItemDecoration(MyDividerItemDecoration(context, LinearLayoutManager.HORIZONTAL, 16, false))
        }

        // test input
        mPopularAdapter.addItem(Festival("07.27 ~ 07.29", "0", "2012 지산 밸리 록 페스티벌"))
        mPopularAdapter.addItem(Festival("08.11 ~ 08.13", "1", "2017 인천 펜사포트 락 페스티벌"))
        mPopularAdapter.addItem(Festival("08.11 ~ 08.13", "2", "2017 인천 펜사포트 락 페스티벌"))
        mPopularAdapter.addItem(Festival("07.29 ~ 07.31", "3", "2011 지산 밸리 록 페스티벌"))
        mPopularAdapter.addItem(Festival("09.14 ~ 09.15", "4", "렛츠락 페스티벌"))

        mImmediateAdapter.addItem(Festival("09.14 ~ 09.15", "4", "렛츠락 페스티벌"))
        mImmediateAdapter.addItem(Festival("07.29 ~ 07.31", "3", "2011 지산 밸리 록 페스티벌"))
        mImmediateAdapter.addItem(Festival("08.11 ~ 08.13", "2", "2017 인천 펜사포트 락 페스티벌"))
        mImmediateAdapter.addItem(Festival("08.11 ~ 08.13", "1", "2017 인천 펜사포트 락 페스티벌"))
        mImmediateAdapter.addItem(Festival("07.27 ~ 07.29", "0", "2012 지산 밸리 록 페스티벌"))

        return rootView
    }

    override fun onResume() {
        super.onResume()
    }

    override fun intentToFestivalDetailActivity(position: Int) {
        activity.startActivity<FestivalDetailActivity>("position" to position)
    }

    override fun clickAgain() {
    }

}