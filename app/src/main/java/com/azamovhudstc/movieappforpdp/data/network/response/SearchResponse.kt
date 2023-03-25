package com.azamovhudstc.movieappforpdp.data.network.response

import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity

data class SearchResponse(
    val page: Int,
    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
)