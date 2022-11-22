package com.example.exchangerate.data

import com.example.exchangerate.CurrencyModel

interface CurrencyRepo {
    suspend fun getCurrencyFromRepo():List<CurrencyModel>
}