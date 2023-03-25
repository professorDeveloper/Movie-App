package com.azamovhudstc.movieappforpdp.di

import com.azamovhudstc.movieappforpdp.repository.*
import com.azamovhudstc.movieappforpdp.repository.imp.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getMovieRepository(impl: MovieRepositoryImp): MovieRepository


    @Binds
    fun getMovieDetailRepository(impl: MovieDetailRepositoryImp): MovieDetailRepository

    @Binds
    fun getSearchRepository(impl:SearchRepositoryImp):SearchRepository

    @Binds
    fun getBookMarkRepo(impl: BookMarkRepositoryImp):BookMarkRepository

    @Binds
    fun getVideosRepo(imp: WatchVideoRepositoryImp):WatchVideoRepository

}