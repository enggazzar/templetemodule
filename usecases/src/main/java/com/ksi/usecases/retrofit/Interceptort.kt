package com.ksi.usecase.retrofit


import com.ksi.usecase.applicationLiveData
import com.ksi.usecase.buildHeader
import com.ksi.usecase.getApplication
import com.ksi.usecases.BuildConfig
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

fun provideHeaderInterceptort(): Interceptor {
    return Interceptor {
        val original = it.request()
        var map = HashMap<String, String>()
        val request = original.newBuilder()
            .headers(Headers.of(buildHeader(applicationLiveData.getApplication())))
            .method(original.method(), original.body())
            .build()
        it.proceed(request)
    }
}


fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return if (BuildConfig.DEBUG)
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    else
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
}