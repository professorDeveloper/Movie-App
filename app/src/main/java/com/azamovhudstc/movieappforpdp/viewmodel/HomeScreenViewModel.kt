package com.azamovhudstc.movieappforpdp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity

interface HomeScreenViewModel {
    var errorMessageLiveData:MutableLiveData<String>
    var movieListLiveData:MutableLiveData<List<MovieEntity>>
    var recentMovieLiveData:MutableLiveData<List<LastMovieEntity>>
    var progressLiveData:MutableLiveData<Boolean>
    fun loadData()
    fun loadDataRecent()
}