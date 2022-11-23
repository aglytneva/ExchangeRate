package com.example

import com.example.exchangerate.CurrencyModel
import com.example.exchangerate.base.Event

data class ViewState(
    val list: List<CurrencyModel>,
    val sortVisible:Boolean,
    val date: String
)

sealed class DataEvent : Event {
    object OnRatesLoaded :DataEvent()
    object OnDateLoaded :DataEvent()
    data class OnCurrencyRatesLoadedSucceed ( val list:List<CurrencyModel>):DataEvent()
    data class OnDateLoadedSucceed ( val date:String):DataEvent()
}

sealed class UiEvent : Event {
    object OnSortButtonClicked :UiEvent()
    object SortAZ :UiEvent()
    object SortZA :UiEvent()
    object SortMaxMin :UiEvent()
    object SortMinMax :UiEvent()
}
