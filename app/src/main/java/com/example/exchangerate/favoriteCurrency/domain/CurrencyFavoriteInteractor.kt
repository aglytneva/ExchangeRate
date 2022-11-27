package com.example.exchangerate.favoriteCurrency.domain

import com.example.exchangerate.base.Either
import com.example.exchangerate.base.attempt
import com.example.exchangerate.favoriteCurrency.local.CurrencyRepository
import com.example.exchangerate.maincurrency.domain.CurrencyModel


class CurrencyFavoriteInteractor (private val currencyLocalRepository: CurrencyRepository) {

    suspend  fun create(model: CurrencyModel) {
        attempt {currencyLocalRepository.create(model)}
    }

    suspend fun read(): Either<Throwable, List<CurrencyModel>> {
        return attempt { currencyLocalRepository.read() }
    }

    suspend fun update(model: CurrencyModel) {
        attempt {currencyLocalRepository.update(model)}
    }

    suspend fun delete(model: CurrencyModel) {
        attempt {currencyLocalRepository.delete(model)}
    }
}