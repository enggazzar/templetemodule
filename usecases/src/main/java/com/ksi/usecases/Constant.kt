package com.ksi.usecase


import android.content.Context
import com.ksi.usecases.enumfiles.EnumShared
import com.ksi.usecases.prefGetString
import java.util.*

var urlAr = "https://img.alsoug.com/"
var urlEn = "https://img.alsoug.com/en/"
var localValue = "d5ab8dc7ef67ca92e41d730982c5c602"
var apiValue = localValue
var apiKey = "ApiKey"
var Authorization = "Authorization"


fun getBaseUrlUseCase(): String {

    val cnt = applicationLiveData.getApplication()
    val lang = cnt.prefGetString(EnumShared.shLanguage.name, "ar")
    val uri: String
    if (lang != "ar") {
        uri = urlAr + lang + "/"
    } else
        uri = urlAr
    return uri
}
fun isLanguageArabic():Boolean{
    val cnt = applicationLiveData.getApplication()
    val lang = cnt.prefGetString(EnumShared.shLanguage.name, "ar")
    if(lang.equals("en")){
       return false
    }else{
        return true
    }
}

fun buildHeader(cnt: Context): HashMap<String, String> {

    val meMap = HashMap<String, String>()
    meMap[apiKey] = apiValue

    /*  try {
          for (s in meMap.keys) {
              val value = meMap[s].toString()
              println("$s:$value")
              Log.e("headralsoug", "$s:$value")
          }
      } catch (e: Exception) {

      }*/

    return meMap
}