package com.azamovhudstc.movieappforpdp.data.network.response

import androidx.room.TypeConverters
import java.io.Serializable

data class FullMovieResponse(
    val adult: Boolean,
    val backdrop_path: String?=null,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String? = null,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany>,

    val release_date: String,
    val revenue: Long? = null,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
):Serializable