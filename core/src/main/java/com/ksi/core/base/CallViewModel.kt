package com.ksi.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksi.usecase.retrofit.getCall


class CallViewModel() : ViewModel() {

    //val responseLiveData = MutableLiveData <HashMap<String, Any>>()
    val responseLiveData = MutableLiveData<Any>()
    val showProgress = MutableLiveData<Boolean>()

    fun call() {
     //  showProgress.postValue(true)
        var map = HashMap<String, String>()
        getCall(responseLiveData,showProgress, map)
       // showProgress.postValue(false)
    }

}