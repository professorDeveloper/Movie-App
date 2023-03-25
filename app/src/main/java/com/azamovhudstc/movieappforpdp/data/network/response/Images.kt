package com.azamovhudstc.movieappforpdp.data.network.response

data class Images(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)