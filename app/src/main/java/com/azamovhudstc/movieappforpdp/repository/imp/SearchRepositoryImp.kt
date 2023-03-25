package com.azamovhudstc.movieappforpdp.repository.imp

import com.azamovhudstc.movieappforpdp.data.network.api.MovieApi
import com.azamovhudstc.movieappforpdp.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(private val searchApi:MovieApi):SearchRepository {
    override fun searchMovieByQuery(query: String)= flow{
        val response =searchApi.getSearchedMovie(query)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!.results))
        }else{
            emit(Result.failure(Exception(response.errorBody()!!.string().toString())))
        }
    }
}