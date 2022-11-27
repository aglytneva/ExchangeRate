package com.example.exchangerate.favoriteCurrency.ui

import com.example.exchangerate.maincurrency.domain.CurrencyModel
import com.example.exchangerate.base.Event

data class ViewState(
    val favoritelist: List<CurrencyModel>,
    val currencyFavoriteShownList: List<CurrencyModel>,
    val sortVisible:Boolean,
    val date: String,
    var edittext:String,
    val actualList: List<CurrencyModel>,
)

sealed class DataEvent : Event {
    object LoadFavoriteCurrency:DataEvent()
    object OnRatesLoaded : DataEvent()
    object OnDateLoaded : DataEvent()
    data class OnFavoriteCurrencyIsLoaded(val favoriteCurrencylist: List<CurrencyModel>):DataEvent()
    data class OnCurrencyRatesLoadedSucceed ( val list:List<CurrencyModel>): DataEvent()
    data class OnDateLoadedSucceed ( val date:String): DataEvent()
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
