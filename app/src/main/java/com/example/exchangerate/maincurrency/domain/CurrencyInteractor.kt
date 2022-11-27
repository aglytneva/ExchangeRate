package com.example.exchangerate.maincurrency.domain

import com.example.exchangerate.base.Either
import com.example.exchangerate.base.attempt
import com.example.exchangerate.maincurrency.data.CurrencyRepo


class CurrencyInteractor(private val currencyRepo: CurrencyRepo) {

    suspend fun getCurrencyRates(): Either<Throwable, List<CurrencyModel>> {
        return attempt { currencyRepo.getCurrencyFromRepo() }
    }
    suspend fun getDate(): Either<Throwable, String> {
        return attempt { currencyRepo.getDateFromRepo()}
    }

}