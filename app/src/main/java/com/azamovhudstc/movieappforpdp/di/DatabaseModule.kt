package com.azamovhudstc.movieappforpdp.di

import android.content.Context
import androidx.room.Room
import com.azamovhudstc.movieappforpdp.data.local.dao.MovieDao
import com.azamovhudstc.movieappforpdp.data.local.dao.MoviesDao
import com.azamovhudstc.movieappforpdp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DatabaseModule {
    @[Provides Singleton]
    fun provideVideosDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .build()

    }

    @Provides
    fun provideVideosDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }
    @Provides
    fun provideBookMark(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao()
    }
}