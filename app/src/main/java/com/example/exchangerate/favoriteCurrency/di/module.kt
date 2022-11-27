package com.example.exchangerate.favoriteCurrency.di

import androidx.room.Room
import com.example.exchangerate.favoriteCurrency.AppDataBase
import com.example.exchangerate.favoriteCurrency.domain.CurrencyFavoriteInteractor
import com.example.exchangerate.favoriteCurrency.local.CurrencyLocalSourse
import com.example.exchangerate.favoriteCurrency.local.CurrencyRepository
import com.example.exchangerate.favoriteCurrency.local.CurrencyRepositoryImpl
import com.example.exchangerate.favoriteCurrency.ui.FavoriteFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


const val CURRENCY_TABLE ="CURRENCY_TABLE"
const val APP_DATABASE ="APP_DATABASE"

val favoriteCurrencyModel =module {

    single {
        CurrencyLocalSourse(currencyDao = get ())

    }
    single <CurrencyRepository> {
        CurrencyRepositoryImpl(currencyLocalSourse = get())

    }

    single {
        CurrencyFavoriteInteractor (currencyLocalRepository = get())
    }

    viewModel {
        FavoriteFragmentViewModel(interactor = get(), allInteractor = get())
    }
}
val databaseModule = module {

    single {
        Room
            .databaseBuilder(androidContext(), AppDataBase::class.java, APP_DATABASE)
            .fallbackToDestructiveMigration() //если меняется база данных
            .build()
    }
    single {
        get<AppDataBase>().bookMarksDao()
    }// берем объект из скоупа нашего коина и вызываем функцию запроса, которая нам вернет объект
}