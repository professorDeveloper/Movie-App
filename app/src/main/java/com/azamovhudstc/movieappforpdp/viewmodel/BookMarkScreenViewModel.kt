package com.azamovhudstc.movieappforpdp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity

interface BookMarkScreenViewModel {
     val bookMarkListLiveData:MutableLiveData<List<LastMovieEntity>>
     fun loadBookMarkList()
}