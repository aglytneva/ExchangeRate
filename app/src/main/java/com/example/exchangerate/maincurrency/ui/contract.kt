package com.example

import com.example.exchangerate.maincurrency.domain.CurrencyModel
import com.example.exchangerate.base.Event

data class ViewState(
    val list: List<CurrencyModel>,
    val currencyShownList: List<CurrencyModel>,
    val sortVisible:Boolean,
    val date: String,
    var edittext:String,
    val favoriteCurrencyList: List<String>
)

sealed class DataEvent : Event {
    object OnRatesLoaded :DataEvent()
    object OnDateLoaded :DataEvent()
    object LoadFavoriteCurrency:DataEvent()
    data class OnCurrencyRatesLoadedSucceed ( val list:List<CurrencyModel>):DataEvent()
    data class OnDateLoadedSucceed ( val date:String):DataEvent()
    data class OnFavoriteCurrencyIsLoaded ( val favoriteCurrencylist:List<CurrencyModel>):DataEvent()
}

sealed class UiEvent : Event {
    data class OnCurrencyClicked(val index: Int) : UiEvent()
    object OnSortButtonClicked :UiEvent()
    object SortAZ :UiEvent()
    object SortZA :UiEvent()
    object SortMaxMin :UiEvent()
    object SortMinMax :UiEvent()
    data class OnSearchEdit (val text :String) :UiEvent()

}
