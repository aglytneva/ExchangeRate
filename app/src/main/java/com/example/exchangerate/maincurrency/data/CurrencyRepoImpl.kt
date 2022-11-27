package com.example.exchangerate.maincurrency.data

import com.example.exchangerate.maincurrency.domain.CurrencyModel

class CurrencyRepoImpl(val source: CurrencyRemoteSource) : CurrencyRepo {
    override suspend fun getCurrencyFromRepo(): List<CurrencyModel> {
        return source.getRates().Valute.toDomain()
//        return source.getRates().Valute.map { it.valute }
//        Valute!!.map{it.valute}.toList().map { it!!.toDomain() }.toList()

    }

    override suspend fun getDateFromRepo(): String {
        return formatToDayMonth(source.getRates().Date.toString())
    }
}
