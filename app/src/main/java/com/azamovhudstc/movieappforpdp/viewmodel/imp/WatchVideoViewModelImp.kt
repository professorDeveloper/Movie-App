package com.azamovhudstc.movieappforpdp.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.movieappforpdp.data.network.response.ResultXX
import com.azamovhudstc.movieappforpdp.repository.WatchVideoRepository
import com.azamovhudstc.movieappforpdp.viewmodel.WatchVideoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WatchVideoViewModelImp @Inject constructor(private val repository: WatchVideoRepository):ViewModel(),WatchVideoViewModel {
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val messageLiveData: MutableLiveData<String> = MutableLiveData()
    override val resultLiveData: MutableLiveData<List<ResultXX>> = MutableLiveData()

    override fun loadVideosDataById(id: Int) {
        progressLiveData.value=true
        repository.watchVideoListById(id).onEach {
            it.onFailure {
                progressLiveData.value=false
                messageLiveData.value=it.message.toString()
            }
            it.onSuccess {
                progressLiveData.value=false
                resultLiveData.value=it
            }
        }.launchIn(viewModelScope)
    }
}