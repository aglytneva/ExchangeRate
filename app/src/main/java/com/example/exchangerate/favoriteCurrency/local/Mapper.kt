package com.example.exchangerate.favoriteCurrency.local

import com.example.exchangerate.favoriteCurrency.local.model.CurrencyEntity
import com.example.exchangerate.maincurrency.domain.CurrencyModel

fun CurrencyEntity.toDomain ()= CurrencyModel (
    code = code,
    currency = currency,
    value = value,
    previous = previous,
    favoriteCurrency = favoriteCurrency

)

fun CurrencyModel.toEntity ()= CurrencyEntity (
    code=code,
    currency = currency,
    value = value,
    previous = previous,
    favoriteCurrency = favoriteCurrency
)