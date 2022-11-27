package com.example.exchangerate.maincurrency.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.DataEvent
import com.example.UiEvent
import com.example.ViewState
import com.example.exchangerate.base.BaseViewModel
import com.example.exchangerate.base.Event
import com.example.exchangerate.favoriteCurrency.domain.CurrencyFavoriteInteractor
import com.example.exchangerate.maincurrency.domain.CurrencyInteractor
import kotlinx.coroutines.launch

class MainViewModel(
    val interactor: CurrencyInteractor,
    val favoriteInteractor: CurrencyFavoriteInteractor
) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState =
        ViewState(
            list = emptyList(),
            currencyShownList = emptyList(),
            sortVisible = false,
            date = "",
            edittext = "",
            favoriteCurrencyList = emptyList()
        )

    init {
        processDataEvent(DataEvent.OnRatesLoaded)
        processDataEvent(DataEvent.OnDateLoaded)
        processDataEvent(DataEvent.LoadFavoriteCurrency)
        Log.d("My Tag", "Данные инициализированы во вью модели")
    }

    override fun reduce(event: Event, previousSTATE: ViewState): ViewState? {
        when (event) {

            is DataEvent.OnRatesLoaded -> {
                viewModelScope.launch {
                    interactor.getCurrencyRates().fold(
                        onError = {
                            Log.d("My Tag", it.localizedMessage)
                        },
                        onSuccess = {
                            Log.d("My Tag", "Данные загружены")
                            processDataEvent(
                                DataEvent.OnCurrencyRatesLoadedSucceed(it)
                            )
                            Log.d("My Tag", it.toString())
                        }

                    )
                }
                return previousSTATE.copy()
            }
            is DataEvent.OnDateLoaded -> {
                viewModelScope.launch {
                    interactor.getDate().fold(
                        onError = {
                            Log.d("My Tag", it.localizedMessage)
                        },
                        onSuccess = {
                            Log.d("My Tag", "Дата загружена")
                            processDataEvent(
                                DataEvent.OnDateLoadedSucceed(it)
                            )
                            Log.d("My Tag", it.toString())
                        }

                    )
                }
                return previousSTATE.copy()
            }
            is DataEvent.LoadFavoriteCurrency ->
                viewModelScope.launch {
                    favoriteInteractor.read().fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(
                                DataEvent
                                    .OnFavoriteCurrencyIsLoaded(it)
                            )
                        }
                    )
                }

            is DataEvent.OnFavoriteCurrencyIsLoaded ->
                return previousSTATE.copy(
                    favoriteCurrencyList = event.favoriteCurrencylist.map { it.code }
                )

            is DataEvent.OnCurrencyRatesLoadedSucceed -> {
                val changedlist = event.list
                val favoriteCurrency = previousSTATE.favoriteCurrencyList
                for (currency in changedlist) {
                    for (favCur in favoriteCurrency) {
                        if (currency.code == favCur) {
                            currency.favoriteCurrency = true
                        }
                    }

                }
                Log.d("My changedList", changedlist.toString() )
                return previousSTATE.copy(
                    list = changedlist,
                    currencyShownList = changedlist
                )

            }

            is UiEvent.OnCurrencyClicked -> {
                viewModelScope.launch {
                    favoriteInteractor.create(previousSTATE.list[event.index])
                    Log.d("My Tag", previousSTATE.list[event.index].toString())
                    Log.d("My Tag", favoriteInteractor.read().toString())
                }
                previousSTATE.currencyShownList[event.index].favoriteCurrency = true
                return null
            }
            is DataEvent.OnDateLoadedSucceed ->
                return previousSTATE.copy(date = event.date)

            is UiEvent.OnSortButtonClicked ->
                return previousSTATE.copy(sortVisible = !previousSTATE.sortVisible)

            is UiEvent.SortAZ ->
                return previousSTATE.copy(
                    currencyShownList = previousSTATE.currencyShownList.sortedBy { it.code.first() },
                    sortVisible = !previousSTATE.sortVisible
                )

            is UiEvent.SortZA ->
                return previousSTATE.copy(
                    currencyShownList = previousSTATE.currencyShownList.sortedByDescending { it.code.first() },
                    sortVisible = !previousSTATE.sortVisible
                )

            is UiEvent.SortMinMax ->
                return previousSTATE.copy(
                    currencyShownList = previousSTATE.currencyShownList.sortedBy { it.value.toFloat() },
                    sortVisible = !previousSTATE.sortVisible
                )

            is UiEvent.SortMaxMin ->
                return previousSTATE.copy(
                    currencyShownList = previousSTATE.currencyShownList.sortedByDescending { it.value.toFloat() },
                    sortVisible = !previousSTATE.sortVisible
                )

            is UiEvent.OnSearchEdit -> {
                previousSTATE.edittext = event.text
                return previousSTATE.copy(
//                    edittext = event.text,
                    currencyShownList = previousSTATE.list.filter {
                        it.currency.toUpperCase()
                            .contains(event.text.toUpperCase()) || it.code.toUpperCase()
                            .contains(event.text.toUpperCase())
                    },
                )
            }
        }
        return null
    }
}