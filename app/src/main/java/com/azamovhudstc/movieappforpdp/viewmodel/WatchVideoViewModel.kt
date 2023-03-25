package com.azamovhudstc.movieappforpdp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.movieappforpdp.data.network.response.ResultXX

interface WatchVideoViewModel {
    val progressLiveData:MutableLiveData<Boolean>
    val messageLiveData:MutableLiveData<String>
    val resultLiveData:MutableLiveData<List<ResultXX>>

    fun loadVideosDataById(id:Int)
}