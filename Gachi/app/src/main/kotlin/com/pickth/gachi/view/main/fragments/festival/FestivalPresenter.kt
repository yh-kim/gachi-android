package com.pickth.gachi.view.main.fragments.festival

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.net.service.FestivalService
import com.pickth.gachi.util.OnFestivalClickListener
import com.pickth.gachi.view.festival.adapter.FestivalDetailAdapter
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival
import com.pickth.gachi.view.main.fragments.festival.adapter.FestivalAdapter
import com.pickth.gachi.view.main.fragments.gachi.adapter.Gachi
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by yonghoon on 2017-07-20.
 * Mail   : yonghoon.kim@pickth.com
 */
class FestivalPresenter: FestivalContract.Presenter, OnFestivalClickListener {

    private lateinit var mView: FestivalContract.View
    private lateinit var mPopularAdapter: FestivalAdapter
    private lateinit var mPopularGachiAdapter: FestivalDetailAdapter

    override fun attachView(view: BaseView<*>) {
        this.mView = view as FestivalContract.View
    }

    override fun setPopularAdapter(adapter: FestivalAdapter) {
        mPopularAdapter = adapter
        mPopularAdapter.setItemClickListener(this)
    }

    override fun setPopularGachiAdapter(adapter: FestivalDetailAdapter) {
        mPopularGachiAdapter = adapter
//        mPopularGachiAdapter.setItemClickListener(this)
    }

    override fun getPopularFestivalItem(position: Int): Festival = mPopularAdapter.getItem(position)

    override fun getPopularGachiItem(position: Int): Gachi = mPopularGachiAdapter.getItem(position)

    override fun getPopularFestivalList() {
        FestivalService().getFestivalList(1)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        if(response?.code() != 200) return

                        val retArr = JSONArray(response.body()!!.string())
                        for(position in 0..retArr.length() - 1) {
                            retArr.getJSONObject(position).let {
                                val fid = it.getString("fid")
                                val title = it.getString("title")
                                val image = it.getString("image")
                                val from = it.getString("from").split("T")[0].replace("-",".")
                                val until = it.getString("until").split("T")[0].replace("-",".")
                                val type = "popular"

                                var date = "$from - $until"
                                mPopularAdapter.addItem(Festival(fid, date, image, title, type))
                            }
                        }
                    }

                })
    }

    override fun getPopularGachiList() {
        for(i in 0..4) mPopularGachiAdapter.addItem(Gachi("a", 0))
    }

    override fun onPopularFestivalClick(position: Int) {
        val fid = mPopularAdapter.getItem(position).fid
        mView.intentToFestivalDetailActivity(fid)
    }

    override fun onImmediateFestivalClick(position: Int) {
//        val fid = mPopularGachiAdapter.getItem(position).fid
//        mView.intentToFestivalDetailActivity(fid)
    }

}