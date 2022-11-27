package com.example.exchangerate.maincurrency.domain

data class CurrencyModel (
    val currency :String,
    val value:String,
    val previous:String,
    val code:String,
    var favoriteCurrency:Boolean=false
)