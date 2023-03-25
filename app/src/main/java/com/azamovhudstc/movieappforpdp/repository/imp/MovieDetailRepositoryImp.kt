package com.azamovhudstc.movieappforpdp.repository.imp

import com.azamovhudstc.movieappforpdp.data.local.dao.MovieDao
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.data.network.api.MovieApi
import com.azamovhudstc.movieappforpdp.data.network.response.Backdrop
import com.azamovhudstc.movieappforpdp.data.network.response.Cast
import com.azamovhudstc.movieappforpdp.data.network.response.FullMovieResponse
import com.azamovhudstc.movieappforpdp.repository.MovieDetailRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) : MovieDetailRepository {
    override fun getMovieDataById(id: Int)= flow{
        val response =movieApi.getMovieById(id)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!))
        }else{
            emit(Result.failure(Exception(response.errorBody()?.string().toString())))
        }
    }

    override fun updateMovieData(movieEntity: LastMovieEntity)= flow {
        movieDao.updateMovies(movieEntity)
        emit(Result.success(Unit))
    }

    override fun addMovieData(movieEntity: LastMovieEntity)= flow {
        movieDao.addMovies(movieEntity)
        emit(Result.success(Unit))
    }

    override fun getImagesDataById(id: Int)= flow{
        emit(Result.success(movieApi.getMovieImages(id).backdrops!!))
    }

    override fun getAuthorsDataById(id: Int)= flow {
        val response =movieApi.getAuthorsById(id)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!.cast))
        }else{
            emit( Result.failure(Exception(response.errorBody()!!.string())))
        }

    }

    override fun addBookMarkData(lastMovieEntity: LastMovieEntity)= flow <Result<Unit>>{

    }

    override fun addLastRecentData(lastMovieEntity: LastMovieEntity)= flow<Result<Unit>> {
        movieDao.addMovies(lastMovieEntity)
        emit(Result.success(Unit))
    }

    override fun updateLastRecentData(lastMovieEntity: LastMovieEntity)= flow<Result<Unit>> {

        movieDao.updateMovies(lastMovieEntity)

    }

    override fun localDataById(id: Int)=flow<Result<LastMovieEntity?>> {
        emit(Result.success(movieDao.getMovieById(id)))
    }

    override fun getLastIndex()= flow <Int?>{
        emit(movieDao.getLastMovieIndex())

    }


}

