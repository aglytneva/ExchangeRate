package com.example

import com.example.exchangerate.CurrencyModel
import com.example.exchangerate.base.Event

data class ViewState(
    val list: List<CurrencyModel>
)

sealed class DataEvent : Event {
    object OnRatesLoaded :DataEvent()
    data class OnCurrencyRatesLoadedSucceed ( val list:List<CurrencyModel>):DataEvent()
}
