package com.azamovhudstc.movieappforpdp.data.network.api

import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.network.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String = "59a02a732ba859a065a5860b822ffbe4",
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path(value = "movie_id", encoded = false) movie_id: Int,
        @Query("api_key") api_key: String = "59a02a732ba859a065a5860b822ffbe4",
    ): Response<LastMovieEntity>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path(value = "movie_id", encoded = false) movie_id: Int,
        @Query("api_key") api_key: String = "59a02a732ba859a065a5860b822ffbe4",
    ): Images

    @GET("search/movie")
    suspend fun getSearchedMovie(
        @Query("query") query: String,
        @Query("api_key") api_key: String = "59a02a732ba859a065a5860b822ffbe4",
    ): Response<SearchResponse>


    @GET("movie/{movie_id}/credits")
    suspend fun  getAuthorsById(
        @Path(value = "movie_id", encoded = false) movie_id: Int,
        @Query("api_key") api_key: String = "59a02a732ba859a065a5860b822ffbe4",
    ):Response<AuthorResponse>


    @GET("movie/{movie_id}/videos")
    suspend fun getVideosById(
        @Path(value = "movie_id", encoded = false) movie_id: Int,
        @Query("api_key") api_key: String = "59a02a732ba859a065a5860b822ffbe4",
        ):Response<VideoResponse>



}