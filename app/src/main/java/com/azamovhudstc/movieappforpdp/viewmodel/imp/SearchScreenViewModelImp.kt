package com.azamovhudstc.movieappforpdp.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.repository.imp.SearchRepositoryImp
import com.azamovhudstc.movieappforpdp.viewmodel.SearchScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModelImp @Inject constructor(private val repositoryImp: SearchRepositoryImp):SearchScreenViewModel,ViewModel() {
    override var messageLiveData: MutableLiveData<String> = MutableLiveData()
    override var progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override var searchMoviesLiveData: MutableLiveData<List<MovieEntity>> = MutableLiveData()

    override fun searchDataByQuery(query: String) {
        progressLiveData.value=true
        repositoryImp.searchMovieByQuery(query).onEach {
            it.onSuccess {
                progressLiveData.value=false
                searchMoviesLiveData.value=it
            }
            it.onFailure {
                progressLiveData.value=false
                messageLiveData.value=it.message
            }
        }.launchIn(viewModelScope
        )
    }
}