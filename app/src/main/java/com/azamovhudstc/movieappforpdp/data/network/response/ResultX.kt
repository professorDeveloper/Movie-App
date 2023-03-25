package com.azamovhudstc.movieappforpdp.data.network.response

import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity

data class ResultX(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){
    fun toEntity()=MovieEntity(adult, backdrop_path, original_language, original_title, overview, popularity, poster_path, release_date, title, video, vote_average, vote_count,id)
}