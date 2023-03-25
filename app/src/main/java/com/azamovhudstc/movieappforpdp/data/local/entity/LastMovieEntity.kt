package com.azamovhudstc.movieappforpdp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.azamovhudstc.movieappforpdp.data.converter.CompanyConverter
import com.azamovhudstc.movieappforpdp.data.converter.GenreConverter
import com.azamovhudstc.movieappforpdp.data.network.response.Genre
import com.azamovhudstc.movieappforpdp.data.network.response.ProductionCompany
import java.io.Serializable

@Entity
data class LastMovieEntity(
    val adult: Boolean,
    val backdrop_path: String? = null,
    // val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    @TypeConverters(GenreConverter::class)
    val genres: List<Genre>,
    val homepage: String,
    @PrimaryKey
    val id: Int,
    val imdb_id: String? = null,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double? = null,
    val poster_path: String? = null,
    @TypeConverters(CompanyConverter::class)
    val production_companies: List<ProductionCompany>,
    //  val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Long? = null,
    val runtime: Int,
    // val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    var isSaved: Boolean = false,
    var index: Int = 0,

    ): Serializable