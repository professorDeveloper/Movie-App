package com.azamovhudstc.movieappforpdp.repository.imp

import com.azamovhudstc.movieappforpdp.data.network.api.MovieApi
import com.azamovhudstc.movieappforpdp.data.network.response.ResultXX
import com.azamovhudstc.movieappforpdp.repository.WatchVideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WatchVideoRepositoryImp @Inject constructor(private val  movieApi: MovieApi):WatchVideoRepository {
    override fun watchVideoListById(id: Int)= flow {
        val response =movieApi.getVideosById(id)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!.results))
        }else{
            emit(Result.failure(Exception(response.errorBody()!!.string().toString())))
        }
    }
}