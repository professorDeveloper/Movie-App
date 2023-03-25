package com.azamovhudstc.movieappforpdp.repository

import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.data.network.response.Backdrop
import com.azamovhudstc.movieappforpdp.data.network.response.Cast
import com.azamovhudstc.movieappforpdp.data.network.response.FullMovieResponse
import kotlinx.coroutines.flow.Flow


interface MovieDetailRepository {
    fun getMovieDataById(id:Int): Flow<Result<LastMovieEntity>>
    fun updateMovieData(movieEntity: LastMovieEntity):Flow<Result<Unit>>
    fun addMovieData(movieEntity: LastMovieEntity):Flow<Result<Unit>>
    fun getImagesDataById(id:Int):Flow<Result<List<Backdrop>>>
    fun getAuthorsDataById(id:Int):Flow<Result<List<Cast>>>
    fun addBookMarkData(lastMovieEntity: LastMovieEntity):Flow<Result<Unit>>
    fun addLastRecentData(lastMovieEntity: LastMovieEntity):Flow<Result<Unit>>
    fun updateLastRecentData(lastMovieEntity: LastMovieEntity):Flow<Result<Unit>>
    fun localDataById(id: Int):Flow<Result<LastMovieEntity?>>
    fun getLastIndex():Flow<Int?>

}