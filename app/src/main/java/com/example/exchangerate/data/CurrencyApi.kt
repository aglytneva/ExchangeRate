package com.example.exchangerate.data

import com.example.exchangerate.data.modelApi.CurrencyRemoteModel
import com.example.exchangerate.data.modelApi.Valute
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js" )
    suspend fun getRates(): CurrencyRemoteModel


}
