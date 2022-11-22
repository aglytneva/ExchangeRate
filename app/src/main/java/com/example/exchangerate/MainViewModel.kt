package com.example.exchangerate

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.DataEvent
import com.example.ViewState
import com.example.exchangerate.base.BaseViewModel
import com.example.exchangerate.base.Event
import kotlinx.coroutines.launch

class MainViewModel(val interactor: CurrencyInteractor) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState =
        ViewState(
            list = emptyList()
        )
init{
    processDataEvent(DataEvent.OnRatesLoaded)

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
                        }

                    )
                }
                return previousSTATE.copy()
            }
            is DataEvent.OnCurrencyRatesLoadedSucceed ->
                return previousSTATE.copy( list = event.list)
        }
        return null
    }
}