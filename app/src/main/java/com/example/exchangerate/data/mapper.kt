package com.example.exchangerate.data

import com.example.exchangerate.CurrencyModel
import com.example.exchangerate.data.modelApi.CurrencyRemoteModel
import com.example.exchangerate.data.modelApi.Valute

import com.example.exchangerate.data.modelApi.ValuteModel
import com.example.exchangerate.data.modelApi.ValuteX

fun ValuteModel.toDomain() = CurrencyModel(
    currency = Name.toString(),
    value = Value.toString()

)


fun ValuteX.toDomain() = listOf<CurrencyModel>(
    CurrencyModel(currency = AMD.Name.toString(), value = AMD.Value.toString()),
    CurrencyModel(currency = AUD.Name.toString(), value = AUD.Value.toString()),
    CurrencyModel(currency = AZN.Name.toString(), value = AZN.Value.toString()),
    CurrencyModel(currency = BGN.Name.toString(), value = BGN.Value.toString()),
    CurrencyModel(currency = BRL.Name.toString(), value = BRL.Value.toString()),
    CurrencyModel(currency = BYN.Name.toString(), value = BYN.Value.toString()),
    CurrencyModel(currency = CAD.Name.toString(), value = CAD.Value.toString()),
    CurrencyModel(currency = CHF.Name.toString(), value = CHF.Value.toString()),
    CurrencyModel(currency = CNY.Name.toString(), value = CNY.Value.toString()),
    CurrencyModel(currency = CZK.Name.toString(), value = CZK.Value.toString()),
    CurrencyModel(currency = DKK.Name.toString(), value = DKK.Value.toString()),
    CurrencyModel(currency = EUR.Name.toString(), value = EUR.Value.toString()),
    CurrencyModel(currency = GBP.Name.toString(), value = GBP.Value.toString()),
    CurrencyModel(currency = HKD.Name.toString(), value = HKD.Value.toString()),
    CurrencyModel(currency = HUF.Name.toString(), value = HUF.Value.toString()),
    CurrencyModel(currency = INR.Name.toString(), value = INR.Value.toString()),
    CurrencyModel(currency = JPY.Name.toString(), value = JPY.Value.toString()),
    CurrencyModel(currency = KGS.Name.toString(), value = KGS.Value.toString()),
    CurrencyModel(currency = KRW.Name.toString(), value = KRW.Value.toString()),
    CurrencyModel(currency = KZT.Name.toString(), value = KZT.Value.toString()),
    CurrencyModel(currency = MDL.Name.toString(), value = MDL.Value.toString()),
    CurrencyModel(currency = NOK.Name.toString(), value = NOK.Value.toString()),
    CurrencyModel(currency = PLN.Name.toString(), value = PLN.Value.toString()),
    CurrencyModel(currency = RON.Name.toString(), value = RON.Value.toString()),
    CurrencyModel(currency = SEK.Name.toString(), value = SEK.Value.toString()),
    CurrencyModel(currency = SGD.Name.toString(), value = SGD.Value.toString()),
    CurrencyModel(currency = TJS.Name.toString(), value = TJS.Value.toString()),
    CurrencyModel(currency = TMT.Name.toString(), value = TMT.Value.toString()),
    CurrencyModel(currency = TRY.Name.toString(), value = TRY.Value.toString()),
    CurrencyModel(currency = UAH.Name.toString(), value = UAH.Value.toString()),
    CurrencyModel(currency = USD.Name.toString(), value = USD.Value.toString()),
    CurrencyModel(currency = UZS.Name.toString(), value = UZS.Value.toString()),


    )

