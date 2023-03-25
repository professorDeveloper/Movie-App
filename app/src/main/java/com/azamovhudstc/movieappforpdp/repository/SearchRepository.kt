
package com.azamovhudstc.movieappforpdp.repository

import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity

interface SearchRepository {
    fun searchMovieByQuery(query:String):kotlinx.coroutines.flow.Flow<Result<List<MovieEntity>>>
}