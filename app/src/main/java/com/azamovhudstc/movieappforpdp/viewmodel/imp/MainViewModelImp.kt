package com.azamovhudstc.movieappforpdp.viewmodel.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamovhudstc.movieappforpdp.viewmodel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImp @Inject constructor():MainViewModel,ViewModel() {
    override val viewPagerLiveData: MutableLiveData<Unit> = MutableLiveData()

    init {
        viewPagerSetup()
    }
    override fun viewPagerSetup() {
        viewPagerLiveData.value=Unit
    }
}