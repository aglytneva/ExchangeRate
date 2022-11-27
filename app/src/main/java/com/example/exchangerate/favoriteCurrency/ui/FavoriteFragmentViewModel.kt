package com.example.exchangerate.favoriteCurrency.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.exchangerate.base.BaseViewModel
import com.example.exchangerate.base.Event
import com.example.exchangerate.favoriteCurrency.domain.CurrencyFavoriteInteractor
import com.example.exchangerate.maincurrency.domain.CurrencyInteractor
import com.example.exchangerate.maincurrency.domain.CurrencyModel
import kotlinx.coroutines.launch


class FavoriteFragmentViewModel(
    val interactor: CurrencyFavoriteInteractor,
    val allInteractor: CurrencyInteractor
) :
    BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState =
        ViewState(
            favoritelist = emptyList(),
            currencyFavoriteShownList = emptyList(),
            sortVisible = false,
            date = "",
            edittext = "",
            actualList = emptyList()
        )

    init {
        processDataEvent(DataEvent.LoadFavoriteCurrency)
        processDataEvent(DataEvent.OnDateLoaded)
        processDataEvent(DataEvent.OnRatesLoaded)
    }

    override fun reduce(event: Event, previousSTATE: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadFavoriteCurrency ->
                viewModelScope.launch {
                    interactor.read().fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(
                                DataEvent
                                    .OnFavoriteCurrencyIsLoaded(it)

                            )
                            Log.d("My Tag Favorite", it.toString())
                        }
                    )
                }

            is DataEvent.OnFavoriteCurrencyIsLoaded ->
                return previousSTATE.copy(
                    favoritelist = event.favoriteCurrencylist,
                    currencyFavoriteShownList = event.favoriteCurrencylist
                )
            is DataEvent.OnRatesLoaded -> {
                viewModelScope.launch {
                    allInteractor.getCurrencyRates().fold(
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
                    allInteractor.getDate().fold(
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
            is DataEvent.OnDateLoadedSucceed ->
                return previousSTATE.copy(date = event.date)

            is DataEvent.OnCurrencyRatesLoadedSucceed -> {
                val actualCurrencyRates = event.list
                val favoriteCurrencyList = previousSTATE.favoritelist
                for (favCur in favoriteCurrencyList) {
                    for (actCur in actualCurrencyRates) {
                        if (actCur.code == favCur.code) {
                            viewModelScope.launch {
                                interactor.update(actCur)
                            }
                        }
                    }
                }
                processDataEvent(DataEvent.LoadFavoriteCurrency)

                return previousSTATE.copy(
                    actualList = event.list,

                )
            }

            is UiEvent.OnCurrencyClicked -> {
                viewModelScope.launch {
                    interactor.delete(previousSTATE.currencyFavoriteShownList[event.index])
                }
                processDataEvent(DataEvent.LoadFavoriteCurrency)
                return null
            }
            is UiEvent.OnSortButtonClicked ->
                return previousSTATE.copy(sortVisible = !previousSTATE.sortVisible)

            is UiEvent.SortAZ ->
                return previousSTATE.copy(
                    currencyFavoriteShownList = previousSTATE.currencyFavoriteShownList.sortedBy { it.code.first() },
                    sortVisible = !previousSTATE.sortVisible
                )

            is UiEvent.SortZA ->
                return previousSTATE.copy(
                    currencyFavoriteShownList = previousSTATE.currencyFavoriteShownList.sortedByDescending { it.code.first() },
                    sortVisible = !previousSTATE.sortVisible
                )

            is UiEvent.SortMinMax ->
                return previousSTATE.copy(
                    currencyFavoriteShownList = previousSTATE.currencyFavoriteShownList.sortedBy { it.value.toFloat() },
                    sortVisible = !previousSTATE.sortVisible
                )

            is UiEvent.SortMaxMin ->
                return previousSTATE.copy(
                    currencyFavoriteShownList = previousSTATE.currencyFavoriteShownList.sortedByDescending { it.value.toFloat() },
                    sortVisible = !previousSTATE.sortVisible
                )

            is UiEvent.OnSearchEdit -> {
                previousSTATE.edittext = event.text
                return previousSTATE.copy(
//                    edittext = event.text,
                    currencyFavoriteShownList = previousSTATE.favoritelist.filter {
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