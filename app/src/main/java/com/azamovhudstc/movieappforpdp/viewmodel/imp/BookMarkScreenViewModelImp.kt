package com.azamovhudstc.movieappforpdp.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.repository.BookMarkRepository
import com.azamovhudstc.movieappforpdp.viewmodel.BookMarkScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookMarkScreenViewModelImp @Inject constructor(private val repo:BookMarkRepository):ViewModel(),BookMarkScreenViewModel{
    override val bookMarkListLiveData: MutableLiveData<List<LastMovieEntity>> = MutableLiveData()
    init {
        loadBookMarkList()
    }
    override fun loadBookMarkList() {
        repo.loadBookMarkData().onEach {
            bookMarkListLiveData.value=it
        }.launchIn(viewModelScope)
    }
}