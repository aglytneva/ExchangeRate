package com.example.exchangerate.maincurrency.data.modelApi

import com.google.gson.annotations.SerializedName

data class CurrencyRemoteModel(
    @SerializedName("Date")
    val Date: String?,
    @SerializedName("PreviousDate")
    val PreviousDate: String?,
    val PreviousURL: String?,
    val Timestamp: String?,
    @SerializedName("Valute")
    val Valute: ValuteX
)