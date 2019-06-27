package com.ksi.usecase

import android.app.Application
import androidx.lifecycle.MutableLiveData

internal val applicationLiveData = MutableLiveData<Application>()

 fun MutableLiveData<Application>.getApplication() = value!!

object Domain {

    fun integrateWith(application: Application) {
        applicationLiveData.value = application
    }

}