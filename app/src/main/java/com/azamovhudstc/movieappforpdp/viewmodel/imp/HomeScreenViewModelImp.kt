package com.azamovhudstc.movieappforpdp.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.repository.imp.MovieRepositoryImp
import com.azamovhudstc.movieappforpdp.utils.hasConnection
import com.azamovhudstc.movieappforpdp.viewmodel.HomeScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModelImp @Inject constructor(private val repo:MovieRepositoryImp) :HomeScreenViewModel,ViewModel(){
    override var errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    override var movieListLiveData: MutableLiveData<List<MovieEntity>> = MutableLiveData()
    override var recentMovieLiveData: MutableLiveData<List<LastMovieEntity>> = MutableLiveData()
    override var progressLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun loadData() {
        progressLiveData.value=true
        if (hasConnection()){
            repo.getMovieList().onEach {
                it.onFailure {
                    progressLiveData.value=false
                    errorMessageLiveData.value=it.message.toString()
                }

                it.onSuccess {
                    progressLiveData.value=false
                    movieListLiveData.value=it

                }
            }.launchIn(viewModelScope)

        }else{
            repo.getLocalMovieList().onEach {
                progressLiveData.value=false
                movieListLiveData.value=it

            }
        }



    }


    override fun loadDataRecent(){
        repo.getRecentMovieList().onEach {
            it.onSuccess {
                recentMovieLiveData.value=it
            }
        }.launchIn(viewModelScope)
    }


}