package com.example.exchangerate.favoriteCurrency.local

import androidx.room.*
import com.example.exchangerate.favoriteCurrency.di.CURRENCY_TABLE
import com.example.exchangerate.favoriteCurrency.local.model.CurrencyEntity

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create (entity: CurrencyEntity)

    @Query("SELECT * FROM $CURRENCY_TABLE")
    suspend fun read(): List<CurrencyEntity>

    @Update
    suspend fun update (entity: CurrencyEntity)

    @Delete
    suspend fun delete (entity: CurrencyEntity)
}