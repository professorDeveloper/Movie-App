package com.azamovhudstc.movieappforpdp.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.data.network.response.Backdrop
import com.azamovhudstc.movieappforpdp.data.network.response.Cast
import com.azamovhudstc.movieappforpdp.data.network.response.FullMovieResponse
import com.azamovhudstc.movieappforpdp.repository.MovieDetailRepository
import com.azamovhudstc.movieappforpdp.utils.hasConnection
import com.azamovhudstc.movieappforpdp.viewmodel.MovieDetailScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailScreenViewModelImp @Inject constructor(private val repo: MovieDetailRepository) :
    ViewModel(), MovieDetailScreenViewModel {
    override var fullMovieLiveData: MutableLiveData<LastMovieEntity> = MutableLiveData()
    override var progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val authorsListLiveData: MutableLiveData<List<Cast>> = MutableLiveData()
    override val imagesLiveData: MutableLiveData<List<Backdrop>> = MutableLiveData()
    override var errorMessageLiveData: MutableLiveData<String> = MutableLiveData()

    override fun loadAuthorsDataById(id: Int) {
        repo.getAuthorsDataById(id).onEach {
            it.onSuccess {
                authorsListLiveData.value = it
            }

            it.onFailure {
                errorMessageLiveData.value = it.message.toString()
            }
        }.launchIn(viewModelScope)
    }


    override fun loadImagesDataById(id: Int) {
        repo.getImagesDataById(id).onEach {
            it.onSuccess {
                imagesLiveData.value = it

            }
        }.launchIn(viewModelScope)
    }

    override fun updateData(movieEntity: LastMovieEntity) {
        viewModelScope.launch {
            repo.updateMovieData(movieEntity).onEach {

            }.launchIn(viewModelScope)
        }
    }

    override fun addData(movieEntity: LastMovieEntity) {
        repo.addMovieData(movieEntity).onEach {
        }.launchIn(viewModelScope)
    }

    override fun loadDataById(id: Int) {
        progressLiveData.value = true
        var lastIndex = 0
        var local:LastMovieEntity?=null
        repo.localDataById(id).onEach { it ->
            it.onSuccess { local1 ->
                local =local1
                repo.getLastIndex().onEach {
                    val index = it ?: -1
                    lastIndex = if (local1 != null) {
                        if (index == -1) 0 else if (index == local1.index) index else index!! + 1
                    } else {
                        if (index == -1) 0 else index!! + 1
                    }


                }.launchIn(viewModelScope)
            }

        }.launchIn(viewModelScope)

        if (hasConnection()) {
            repo.getMovieDataById(id).onEach {
                it.onSuccess { data ->
                    progressLiveData.value = false
                    if (local == null) {
                        data.index = lastIndex
                    } else {
                        data.index = lastIndex
                        data.isSaved = local!!.isSaved
                    }
                    addData(data)

                    fullMovieLiveData.value = data
                }

                it.onFailure {
                    progressLiveData.value = false
                    errorMessageLiveData.value = it.message.toString()
                }
            }.launchIn(viewModelScope)
        }else{
            repo.localDataById(id).onEach {
                it.onSuccess {local ->
                    if (local != null) {
                        local.index = lastIndex
                        fullMovieLiveData.value = local

                        addData(local)
                    } else {
                        errorMessageLiveData.value="No Internet Connection"
                    }
                }
            }

        }
    }
}