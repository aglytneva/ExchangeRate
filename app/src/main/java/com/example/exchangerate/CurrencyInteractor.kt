package com.example.exchangerate

import com.example.exchangerate.base.Either
import com.example.exchangerate.base.attempt
import com.example.exchangerate.data.CurrencyRepo


class CurrencyInteractor(private val currencyRepo: CurrencyRepo) {

    suspend fun getCurrencyRates(): Either<Throwable, List<CurrencyModel>> {
        return attempt { currencyRepo.getCurrencyFromRepo() }
    }

}