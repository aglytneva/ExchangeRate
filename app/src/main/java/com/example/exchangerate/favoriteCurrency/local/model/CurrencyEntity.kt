package com.example.exchangerate.favoriteCurrency.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exchangerate.favoriteCurrency.di.CURRENCY_TABLE


@Entity (tableName = CURRENCY_TABLE)
data class CurrencyEntity (

    @PrimaryKey
    @ColumnInfo(name= "code")
    val code :String,
    @ColumnInfo(name= "currency")
    val currency :String,
    @ColumnInfo(name= "value")
    val value:String,
    @ColumnInfo(name= "previous")
    val previous:String,
    @ColumnInfo(name= "favoriteCurrency")
    var favoriteCurrency:Boolean

)