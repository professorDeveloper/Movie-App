package com.azamovhudstc.movieappforpdp.data.local.dao

import androidx.room.*
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<MovieEntity>)

    @Query("select * from movieentity")
    suspend fun getMovies():List<MovieEntity>


    @Query("DELETE FROM MovieEntity")
    suspend fun clear()
}