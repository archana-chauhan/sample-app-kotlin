package com.example.gfgandroidassignment.interfaces

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.gfgandroidassignment.model.Result

interface ApiInterface {
    @GET("v1/api.json")
    fun getData(@Query("rss_url") rss_url: String): Call<Result>
}
