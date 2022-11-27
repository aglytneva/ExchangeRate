package com.example.exchangerate.favoriteCurrency

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exchangerate.favoriteCurrency.local.CurrencyDao
import com.example.exchangerate.favoriteCurrency.local.model.CurrencyEntity

@Database (entities =[ CurrencyEntity::class], version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun bookMarksDao (): CurrencyDao

}