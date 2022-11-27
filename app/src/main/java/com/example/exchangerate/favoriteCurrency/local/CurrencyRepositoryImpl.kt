package com.example.exchangerate.favoriteCurrency.local

import com.example.exchangerate.maincurrency.domain.CurrencyModel


class CurrencyRepositoryImpl (private val currencyLocalSourse: CurrencyLocalSourse) : CurrencyRepository {

    override suspend fun create(model: CurrencyModel) {
        currencyLocalSourse.create(model.toEntity())
    }

    override suspend fun read(): List<CurrencyModel> {
        return currencyLocalSourse.read().map { it.toDomain() }}

    override suspend fun update(model: CurrencyModel) {
        currencyLocalSourse.update(model.toEntity())
    }

    override suspend fun delete(model: CurrencyModel) {
        currencyLocalSourse.delete(model.toEntity())
    }
}
