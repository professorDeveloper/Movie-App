package com.azamovhudstc.movieappforpdp.repository

import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import java.util.concurrent.Flow

interface  BookMarkRepository {

    fun loadBookMarkData():kotlinx.coroutines.flow.Flow<List<LastMovieEntity>>

}