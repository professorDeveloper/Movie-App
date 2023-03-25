package com.azamovhudstc.movieappforpdp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.data.network.response.Backdrop
import com.azamovhudstc.movieappforpdp.data.network.response.Cast
import com.azamovhudstc.movieappforpdp.data.network.response.FullMovieResponse

interface MovieDetailScreenViewModel {
    var fullMovieLiveData:MutableLiveData<LastMovieEntity>
    var progressLiveData:MutableLiveData<Boolean>
    val authorsListLiveData:MutableLiveData<List<Cast>>
    val imagesLiveData:MutableLiveData<List<Backdrop>>
    var errorMessageLiveData:MutableLiveData<String>
    fun loadAuthorsDataById(id: Int)
    fun loadImagesDataById(id:Int   )
    fun updateData(movieEntity: LastMovieEntity)
    fun addData(movieEntity: LastMovieEntity)
    fun loadDataById(id:Int)
}