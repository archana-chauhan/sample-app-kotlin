package com.example.gfgandroidassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gfgandroidassignment.model.Item
import com.example.gfgandroidassignment.service.RetrofitService

class FeedViewModel: ViewModel() {
    private val mService  =  RetrofitService()

    fun getData() : MutableLiveData<List<Item>>? {
        return mService.loadServerData()
    }
}