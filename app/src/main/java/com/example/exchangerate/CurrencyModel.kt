package com.example.exchangerate

data class CurrencyModel (
    val currency :String,
    val value:String,
    val previous:String,
    val code:String,
    var favoriteCurrency:Boolean=false
)