package com.example.exchangerate.maincurrency.data

import com.example.exchangerate.maincurrency.data.modelApi.CurrencyRemoteModel
import com.example.exchangerate.maincurrency.data.modelApi.Valute
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js" )
    suspend fun getRates(): CurrencyRemoteModel


}
