package com.ksi.usecase

import com.ksi.entities.ModelTest
import com.ksi.usecase.retrofit.getApiServiceByLang
import io.reactivex.Single
import retrofit2.http.GET


private const val SERVER_BASE_URL = "https://jsonplaceholder.typicode.com/todos/"
private const val APP_ID_KEY = "appid"
private const val APP_ID_VALUE = "cc8bf0ef9fefd3794a362f69e9b0721d"

//todo
// 2- adapter load more
// 3- dialog ok cancel-list -forget paswword- belong to project


fun determineCall(map: HashMap<String, String>): Single<*> {

    var call: Single<*>? = null
    return  getApiServiceByLang().requestCities()
}


interface ProjectApis {
    @GET("1")
    fun requestCities(
    ): Single<ModelTest>
}


