package com.example.exchangerate.maincurrency.data

import com.example.exchangerate.maincurrency.domain.CurrencyModel

import com.example.exchangerate.maincurrency.data.modelApi.ValuteX
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//fun ValuteModel.toDomain() = CurrencyModel(
//    currency = Name.toString(),
//    value = Value
//
//)


fun ValuteX.toDomain() = listOf(
    CurrencyModel(
        currency = AMD.Name.toString(),
        value = AMD.Value.toString(),
        previous = AMD.Previous.toString(),
        code = AMD.CharCode.toString()
    ),
    CurrencyModel(
        currency = AUD.Name.toString(),
        value = AUD.Value.toString(),
        previous = AUD.Previous.toString(),
        code = AUD.CharCode.toString()
    ),
    CurrencyModel(
        currency = AZN.Name.toString(),
        value = AZN.Value.toString(),
        previous = AZN.Previous.toString(),
        code = AZN.CharCode.toString()
    ),
    CurrencyModel(
        currency = BGN.Name.toString(),
        value = BGN.Value.toString(),
        previous = BGN.Previous.toString(),
        code = BGN.CharCode.toString()
    ),
    CurrencyModel(
        currency = BRL.Name.toString(),
        value = BRL.Value.toString(),
        previous = BRL.Previous.toString(),
        code = BRL.CharCode.toString()
    ),
    CurrencyModel(
        currency = BYN.Name.toString(),
        value = BYN.Value.toString(),
        previous = BYN.Previous.toString(),
        code = BYN.CharCode.toString()
    ),
    CurrencyModel(
        currency = CAD.Name.toString(),
        value = CAD.Value.toString(),
        previous = CAD.Previous.toString(),
        code = CAD.CharCode.toString()
    ),
    CurrencyModel(
        currency = CHF.Name.toString(),
        value = CHF.Value.toString(),
        previous = CHF.Previous.toString(),
        code = CHF.CharCode.toString()
    ),
    CurrencyModel(
        currency = CNY.Name.toString(),
        value = CNY.Value.toString(),
        previous = CNY.Previous.toString(),
        code = CNY.CharCode.toString()
    ),
    CurrencyModel(
        currency = CZK.Name.toString(),
        value = CZK.Value.toString(),
        previous = CZK.Previous.toString(),
        code = CZK.CharCode.toString()
    ),
    CurrencyModel(
        currency = DKK.Name.toString(),
        value = DKK.Value.toString(),
        previous = DKK.Previous.toString(),
        code = DKK.CharCode.toString()
    ),
    CurrencyModel(
        currency = EUR.Name.toString(),
        value = EUR.Value.toString(),
        previous = EUR.Previous.toString(),
        code = EUR.CharCode.toString()
    ),
    CurrencyModel(
        currency = GBP.Name.toString(),
        value = GBP.Value.toString(),
        previous = GBP.Previous.toString(),
        code = GBP.CharCode.toString()
    ),
    CurrencyModel(
        currency = HKD.Name.toString(),
        value = HKD.Value.toString(),
        previous = HKD.Previous.toString(),
        code = HKD.CharCode.toString()
    ),
    CurrencyModel(
        currency = HUF.Name.toString(),
        value = HUF.Value.toString(),
        previous = HUF.Previous.toString(),
        code = HUF.CharCode.toString()
    ),
    CurrencyModel(
        currency = INR.Name.toString(),
        value = INR.Value.toString(),
        previous = INR.Previous.toString(),
        code = INR.CharCode.toString()
    ),
    CurrencyModel(
        currency = JPY.Name.toString(),
        value = JPY.Value.toString(),
        previous = JPY.Previous.toString(),
        code = JPY.CharCode.toString()
    ),
    CurrencyModel(
        currency = KGS.Name.toString(),
        value = KGS.Value.toString(),
        previous = KGS.Previous.toString(),
        code = KGS.CharCode.toString()
    ),
    CurrencyModel(
        currency = KRW.Name.toString(),
        value = KRW.Value.toString(),
        previous = KRW.Previous.toString(),
        code = KRW.CharCode.toString()
    ),
    CurrencyModel(
        currency = KZT.Name.toString(),
        value = KZT.Value.toString(),
        previous = KZT.Previous.toString(),
        code = KZT.CharCode.toString()
    ),
    CurrencyModel(
        currency = MDL.Name.toString(),
        value = MDL.Value.toString(),
        previous = MDL.Previous.toString(),
        code = MDL.CharCode.toString()
    ),
    CurrencyModel(
        currency = NOK.Name.toString(),
        value = NOK.Value.toString(),
        previous = NOK.Previous.toString(),
        code = NOK.CharCode.toString()
    ),
    CurrencyModel(
        currency = PLN.Name.toString(),
        value = PLN.Value.toString(),
        previous = PLN.Previous.toString(),
        code = PLN.CharCode.toString()
    ),
    CurrencyModel(
        currency = RON.Name.toString(),
        value = RON.Value.toString(),
        previous = RON.Previous.toString(),
        code = RON.CharCode.toString()
    ),
    CurrencyModel(
        currency = SEK.Name.toString(),
        value = SEK.Value.toString(),
        previous = SEK.Previous.toString(),
        code = SEK.CharCode.toString()
    ),
    CurrencyModel(
        currency = SGD.Name.toString(),
        value = SGD.Value.toString(),
        previous = SGD.Previous.toString(),
        code = SGD.CharCode.toString()
    ),
    CurrencyModel(
        currency = TJS.Name.toString(),
        value = TJS.Value.toString(),
        previous = TJS.Previous.toString(),
        code = TJS.CharCode.toString()
    ),
    CurrencyModel(
        currency = TMT.Name.toString(),
        value = TMT.Value.toString(),
        previous = TMT.Previous.toString(),
        code = TMT.CharCode.toString()
    ),
    CurrencyModel(
        currency = TRY.Name.toString(),
        value = TRY.Value.toString(),
        previous = TRY.Previous.toString(),
        code = TRY.CharCode.toString()
    ),
    CurrencyModel(
        currency = UAH.Name.toString(),
        value = UAH.Value.toString(),
        previous = UAH.Previous.toString(),
        code = UAH.CharCode.toString()
    ),
    CurrencyModel(
        currency = USD.Name.toString(),
        value = USD.Value.toString(),
        previous = USD.Previous.toString(),
        code = USD.CharCode.toString()
    ),
    CurrencyModel(
        currency = UZS.Name.toString(),
        value = UZS.Value.toString(),
        previous = UZS.Previous.toString(),
        code = UZS.CharCode.toString()
    ),
)

val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'+03:00'")


fun formatToDayMonth(date: String): String {
    return "${LocalDate.parse(date, firstApiFormat).dayOfMonth.toString()}." +
            "${LocalDate.parse(date, firstApiFormat).monthValue.toString()}." +
            "${LocalDate.parse(date, firstApiFormat).year.toString().takeLast(2)}"
}
//"2022-11-24T11:30:00+03:00",
//2010-04-05T17:16:00Z" you can use either "yyyy-MM-dd'T'HH:mm:ssX