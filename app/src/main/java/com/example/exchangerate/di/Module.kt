package com.example.exchangerate.di

import com.example.exchangerate.CurrencyInteractor
import com.example.exchangerate.MainViewModel
import com.example.exchangerate.data.CurrencyApi
import com.example.exchangerate.data.CurrencyRemoteSource
import com.example.exchangerate.data.CurrencyRepo
import com.example.exchangerate.data.CurrencyRepoImpl
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL ="https://www.cbr-xml-daily.ru/"
val currencyMainScreenModule = module {
    /*
    presenter = WetherScreenPresenter(
        WeatherInteractor(
            WeatherRepoImpl(
                WeatherRemoteSource(
                    WeatherApiClient.getApi()
                )
            )
        )
    )
    */

    // создаем okHttpClient
    single<OkHttpClient> { OkHttpClient.Builder().build() }

    //создаем сам ретрофит
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<CurrencyApi> { get<Retrofit>().create(CurrencyApi::class.java) }

    single { CurrencyRemoteSource(get<CurrencyApi>()) }

    single<CurrencyRepo> { CurrencyRepoImpl(get<CurrencyRemoteSource>()) }

    single { CurrencyInteractor(get<CurrencyRepo>()) }

    viewModel { MainViewModel(get<CurrencyInteractor>()) }
}