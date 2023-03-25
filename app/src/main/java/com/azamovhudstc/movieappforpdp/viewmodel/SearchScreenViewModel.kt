package com.azamovhudstc.movieappforpdp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import retrofit2.http.Query

interface SearchScreenViewModel {
    var messageLiveData:MutableLiveData<String>
    var progressLiveData:MutableLiveData<Boolean>
    var searchMoviesLiveData:MutableLiveData<List<MovieEntity>>

    fun searchDataByQuery(query: String)
}