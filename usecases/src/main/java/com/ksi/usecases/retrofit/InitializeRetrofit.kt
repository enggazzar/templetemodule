package com.ksi.usecase.retrofit

import androidx.lifecycle.MutableLiveData
import com.ksi.usecase.*
import com.ksi.usecases.enumfiles.enumApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//lateinit var retrofit: Retrofit

private val retrofit: Retrofit by lazy {

    Retrofit.Builder()
            .baseUrl(getBaseUrlUseCase())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

private val retrofitEn: Retrofit by lazy {

    Retrofit.Builder()
        .baseUrl(getBaseUrlUseCase())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

/*fun resetRetrofit(){
    Log.e("lang",getBaseUrlUseCase())

    retrofit=Retrofit.Builder().baseUrl(getBaseUrlUseCase())
       .addConverterFactory(GsonConverterFactory.create())
       .client(httpClient)
       .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
       .build()
}*/
 object ApiService{
    // init Retrofit base server instance
    val ServiceAr by lazy { ApiService.invoke(urlAr) }
    val ServiceEn by lazy { ApiService.invoke(urlEn) }
    //val stackClient by lazy { RestClient.invoke("") }



    operator fun invoke(baseUrl: String): ProjectApis {


        return Retrofit.Builder()
            .baseUrl(getBaseUrlUseCase())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ProjectApis::class.java)
    }
}
val httpClient by lazy {
    OkHttpClient.Builder()
            .addInterceptor(provideHeaderInterceptort())
            .addInterceptor(provideHttpLoggingInterceptor())
            .readTimeout(6, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .build()
}


fun  getApiServiceByLang(): ProjectApis  {
   if(isLanguageArabic()){
     return ApiService.ServiceAr
   }else{
       return ApiService.ServiceEn

   }
}

fun getCall(resp: MutableLiveData<Any>, showProgress: MutableLiveData<Boolean>, map: HashMap<String, String>) {
 //   resetRetrofit()

    showProgress.postValue(true)
    var call: Single<*> = determineCall(map)
    call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var map=HashMap<String,Any>()
                map.put(enumApi.resp.name,it)
                resp.postValue(it)
                showProgress.postValue(false)
            }, {
                resp.postValue(it)
                showProgress.postValue(false)
            }
            )
}
fun getCallwitFalgs(
    resp: MutableLiveData<HashMap<String, Any>>,
    showProgress: MutableLiveData<Boolean>,
    map: HashMap<String, String>
) {
    //   resetRetrofit()

    showProgress.postValue(true)
    var call: Single<*> = determineCall(map)
    call.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            var mapResp = HashMap<String, Any>()
            mapResp.put(enumApi.resp.name, it)
            resp.postValue(buildResponse(it, map))
            showProgress.postValue(false)
        }, {
            resp.postValue(buildResponse(it, map))
            showProgress.postValue(false)
        }
        )
}

fun buildResponse(resp: Any, map: HashMap<String, String>): HashMap<String, Any> {
    var mapResp = HashMap<String, Any>()
    mapResp.put(enumApi.resp.name, resp)
    mapResp.put(enumApi.flag.name, map)
    return mapResp
}