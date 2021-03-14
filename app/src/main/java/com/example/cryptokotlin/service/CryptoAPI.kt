package com.example.cryptokotlin.service

import com.example.cryptokotlin.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "3eb0f7860bf8c2170288d6776d581a06"
interface CryptoAPI {

    @GET("prices")
    fun getData(@Query("key") key : String ) : Observable<List<CryptoModel>>


}