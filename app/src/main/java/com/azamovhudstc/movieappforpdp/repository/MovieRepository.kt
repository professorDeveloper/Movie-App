package com.azamovhudstc.movieappforpdp.repository

import android.graphics.Movie
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList():Flow<Result<List<MovieEntity>>>
    fun getLocalMovieList():Flow<List<MovieEntity>>
    fun getRecentMovieList():Flow<Result<List<LastMovieEntity>>>

}