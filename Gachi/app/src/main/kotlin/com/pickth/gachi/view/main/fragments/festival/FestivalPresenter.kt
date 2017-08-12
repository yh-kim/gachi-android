package com.pickth.gachi.view.main.fragments.festival

import com.pickth.commons.mvp.BaseView
import com.pickth.gachi.net.service.FestivalService
import com.pickth.gachi.util.OnItemClickListener
import com.pickth.gachi.view.main.fragments.festival.adapter.Festival
import com.pickth.gachi.view.main.fragments.festival.adapter.FestivalAdapter
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                                val title = it.getString("title")
                                val image = it.getString("image")
                                val from = it.getString("from").split("T")[0].replace("-",".")
                                val until = it.getString("until").split("T")[0].replace("-",".")

                                var date = "$from - $until"
                                mPopularAdapter.addItem(Festival(date, image, title))
                            }
                        }
                    }

                })
    }

    override fun getImmediateFestivalList() {
        FestivalService().getFestivalList(1)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    }

                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        if(response?.code() != 200) return

                        val retArr = JSONArray(response.body()!!.string())
                        for(position in 0..retArr.length() - 1) {
                            retArr.getJSONObject(position).let {
                                val title = it.getString("title")
                                val image = it.getString("image")
                                val from = it.getString("from").split("T")[0].replace("-",".")
                                val until = it.getString("until").split("T")[0].replace("-",".")

                                var date = "$from - $until"
                                mImmediateAdapter.addItem(Festival(date, image, title))
                            }
                        }
                    }

                })
    }

    override fun onItemClick(position: Int) {
        mView.intentToFestivalDetailActivity(0)
    }
}