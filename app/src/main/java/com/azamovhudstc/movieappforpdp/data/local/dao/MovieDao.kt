package com.azamovhudstc.movieappforpdp.data.local.dao

import androidx.room.*
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * from LastMovieEntity")
    suspend fun getAllMovies(): List<LastMovieEntity>

    @Query("SELECT * from LastMovieEntity where isSaved = 1")
    suspend fun getLikedMovies( ): List<LastMovieEntity>

    @Query("SELECT MAX(`index`) FROM LastMovieEntity")
    suspend fun getLastMovieIndex(): Int?

    @Query("select * from lastmovieentity where id = :id")
    suspend fun getMovieById(id:Int):LastMovieEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(bookEntities: LastMovieEntity)


    @Delete
    suspend  fun deleteMovies(bookEntities: LastMovieEntity)

    @Update
    suspend fun updateMovies(bookEntities: LastMovieEntity)

    @Query("DELETE FROM LastMovieEntity")
    suspend fun clear()
}