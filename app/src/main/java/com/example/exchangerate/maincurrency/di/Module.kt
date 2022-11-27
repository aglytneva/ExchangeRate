package com.example.exchangerate.maincurrency.di

import com.example.exchangerate.favoriteCurrency.domain.CurrencyFavoriteInteractor
import com.example.exchangerate.maincurrency.domain.CurrencyInteractor
import com.example.exchangerate.maincurrency.ui.MainViewModel
import com.example.exchangerate.maincurrency.data.CurrencyApi
import com.example.exchangerate.maincurrency.data.CurrencyRemoteSource
import com.example.exchangerate.maincurrency.data.CurrencyRepo
import com.example.exchangerate.maincurrency.data.CurrencyRepoImpl
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

    viewModel { MainViewModel(get<CurrencyInteractor>(),get<CurrencyFavoriteInteractor>()) }
}