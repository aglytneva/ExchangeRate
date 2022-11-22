package com.example.exchangerate.data.modelApi

import com.google.gson.annotations.SerializedName

data class ValuteModel(
    @SerializedName("ID")
    val ID:String?,
    @SerializedName("NumCode")
    val NumCode:String?,
    @SerializedName("CharCode")
    val CharCode: String?,
    @SerializedName("Nominal")
    val Nominal: Int?,
    @SerializedName("Name")
    val Name: String?,
    @SerializedName("Value")
    val Value: Float?,
    @SerializedName("Previous")
    val Previous: Float?
)
