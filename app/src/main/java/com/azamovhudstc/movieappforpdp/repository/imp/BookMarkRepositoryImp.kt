package com.azamovhudstc.movieappforpdp.repository.imp

import com.azamovhudstc.movieappforpdp.data.local.dao.MovieDao
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.repository.BookMarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookMarkRepositoryImp @Inject constructor(private val dao:MovieDao):BookMarkRepository {
    override fun loadBookMarkData()= flow {
        emit(dao.getLikedMovies())
    }
}