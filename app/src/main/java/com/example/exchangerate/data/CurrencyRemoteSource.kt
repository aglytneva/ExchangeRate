package com.example.exchangerate.data

import com.example.exchangerate.data.modelApi.CurrencyRemoteModel

class CurrencyRemoteSource(private val api: CurrencyApi) {
    //TO DO add query
    suspend fun getRates(): CurrencyRemoteModel {
        return api.getRates()
    }

}