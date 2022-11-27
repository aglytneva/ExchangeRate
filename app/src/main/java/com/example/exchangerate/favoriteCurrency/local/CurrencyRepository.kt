package com.example.exchangerate.favoriteCurrency.local

import com.example.exchangerate.maincurrency.domain.CurrencyModel


interface CurrencyRepository {

    suspend fun create(model: CurrencyModel)

    suspend fun read(): List<CurrencyModel>

    suspend fun update(model: CurrencyModel)

    suspend fun delete(model: CurrencyModel)

}