package com.example.exchangerate.maincurrency.data

import com.example.exchangerate.maincurrency.data.modelApi.CurrencyRemoteModel

class CurrencyRemoteSource(private val api: CurrencyApi) {
    //TO DO add query
    suspend fun getRates(): CurrencyRemoteModel {
        return api.getRates()
    }

}