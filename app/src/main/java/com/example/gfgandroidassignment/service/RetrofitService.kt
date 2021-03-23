package com.example.gfgandroidassignment.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.gfgandroidassignment.interfaces.ApiInterface
import com.example.gfgandroidassignment.model.Item
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.gfgandroidassignment.model.Result

class RetrofitService {
    val liveDataResponse: MutableLiveData<List<Item>> = MutableLiveData()
    val TAG:String = "RetrofitService"

    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.rss2json.com/")
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

    fun loadServerData(): MutableLiveData<List<Item>>? {
        val retrofitCall  = create().getData("http://www.abc.net.au/news/feed/51120/rss.xml")
        retrofitCall.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable?) {
                Log.e(TAG, "onFailure:: " + Log.getStackTraceString(t))
            }
            override fun onResponse(call: Call<Result>, response: retrofit2.Response<Result>) {
                Log.d(TAG, "onResponse:: " + response.toString())
                val list  = response.body()?.items
                for (i in list.orEmpty()){
                    Log.d(TAG, i.title)
                }
                liveDataResponse.value = list
            }
        })
        return liveDataResponse
    }
}