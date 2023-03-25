package com.azamovhudstc.movieappforpdp.viewmodel

import androidx.lifecycle.MutableLiveData


interface MainViewModel {
    val viewPagerLiveData: MutableLiveData<Unit>
    fun viewPagerSetup()

}