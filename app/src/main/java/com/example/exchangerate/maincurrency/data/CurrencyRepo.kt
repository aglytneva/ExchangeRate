package com.example.exchangerate.maincurrency.data

import com.example.exchangerate.maincurrency.domain.CurrencyModel

interface CurrencyRepo {
    suspend fun getCurrencyFromRepo():List<CurrencyModel>
    suspend fun getDateFromRepo():String
}