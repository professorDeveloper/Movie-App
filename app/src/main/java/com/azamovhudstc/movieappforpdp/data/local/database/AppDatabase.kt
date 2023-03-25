package com.azamovhudstc.movieappforpdp.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.azamovhudstc.movieappforpdp.data.converter.CompanyConverter
import com.azamovhudstc.movieappforpdp.data.converter.GenreConverter
import com.azamovhudstc.movieappforpdp.data.local.dao.MovieDao
import com.azamovhudstc.movieappforpdp.data.local.dao.MoviesDao
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class,LastMovieEntity::class], version = 1)
@TypeConverters(GenreConverter::class, CompanyConverter::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun moviesDao(): MoviesDao


}