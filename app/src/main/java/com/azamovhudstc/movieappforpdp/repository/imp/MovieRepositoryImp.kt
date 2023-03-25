package com.azamovhudstc.movieappforpdp.repository.imp

import com.azamovhudstc.movieappforpdp.data.local.dao.MovieDao
import com.azamovhudstc.movieappforpdp.data.local.dao.MoviesDao
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.data.network.api.MovieApi
import com.azamovhudstc.movieappforpdp.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class MovieRepositoryImp @Inject constructor(private val api: MovieApi,private val dao: MovieDao,private val daoMoviesDao: MoviesDao) :MovieRepository {
    override fun getMovieList()= flow {

         if (api.getPopularMovies().isSuccessful){
             if (daoMoviesDao.getMovies().isEmpty()){
                 daoMoviesDao.insert(api.getPopularMovies().body()!!.results)
             }else{
                 daoMoviesDao.clear()
                 daoMoviesDao.insert(api.getPopularMovies().body()!!.results)
             }
             emit(Result.success(api.getPopularMovies().body()!!.results))
         }
         else{
             emit(Result.failure(Exception(api.getPopularMovies().errorBody()?.string().toString())))
         }
    }

    override fun getLocalMovieList()= flow<List<MovieEntity>> {
        emit(daoMoviesDao.getMovies())
    }

    override fun getRecentMovieList()= flow<Result<List<LastMovieEntity>>>{
        emit(Result.success(dao.getAllMovies()))
    }
}