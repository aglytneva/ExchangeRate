package com.example.exchangerate.favoriteCurrency.local

import com.example.exchangerate.favoriteCurrency.local.model.CurrencyEntity


class CurrencyLocalSourse (private  val currencyDao: CurrencyDao) {

    suspend fun create (entity: CurrencyEntity) {
        currencyDao.create(entity)
    }

    suspend fun read(): List<CurrencyEntity> {
        return  currencyDao.read()
    }


    suspend fun update (entity: CurrencyEntity) {
        currencyDao.update(entity)
    }

    suspend fun delete (entity: CurrencyEntity) {
        currencyDao.delete(entity)
    }

}