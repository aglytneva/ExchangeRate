package com.example.exchangerate

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.DataEvent
import com.example.UiEvent
import com.example.ViewState
import com.example.exchangerate.base.BaseViewModel
import com.example.exchangerate.base.Event
import kotlinx.coroutines.launch

class MainViewModel(val interactor: CurrencyInteractor) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState =
        ViewState(
            list = emptyList(),
            sortVisible = false,
            date = "01.01.01"
        )

    init {
        processDataEvent(DataEvent.OnRatesLoaded)
        processDataEvent(DataEvent.OnDateLoaded)
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
                                DataEvent.OnCurrencyRatesLoadedSucceed(
                                    list = it

                                )
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
                                DataEvent.OnDateLoadedSucceed(
                                    date = it
                                )
                            )
                            Log.d("My Tag", it.toString())
                        }

                    )
                }
                return previousSTATE.copy()
            }

            is DataEvent.OnCurrencyRatesLoadedSucceed ->
                return previousSTATE.copy(list = event.list)

            is DataEvent.OnDateLoadedSucceed ->
                return previousSTATE.copy(date = event.date)

            is UiEvent.OnSortButtonClicked ->
                return previousSTATE.copy(sortVisible = !previousSTATE.sortVisible)

            is UiEvent.SortAZ ->
                return previousSTATE.copy(list = previousSTATE.list.sortedBy { it.currency.first() })

            is UiEvent.SortZA ->
                return previousSTATE.copy(list = previousSTATE.list.sortedByDescending { it.currency.first() })

            is UiEvent.SortMinMax ->
                return previousSTATE.copy(list = previousSTATE.list.sortedBy { it.value.toFloat() })

            is UiEvent.SortMaxMin ->
                return previousSTATE.copy(list = previousSTATE.list.sortedByDescending { it.value.toFloat() })
        }
        return null
    }
}