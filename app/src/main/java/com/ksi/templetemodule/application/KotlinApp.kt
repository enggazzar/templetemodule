package com.ksi.templetemodule.application

import android.app.Application
import com.ksi.usecase.Domain

 class KotlinApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Domain.integrateWith(this)
       // Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(this))
    }
}